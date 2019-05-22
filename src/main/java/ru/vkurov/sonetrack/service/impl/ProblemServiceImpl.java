package ru.vkurov.sonetrack.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vkurov.sonetrack.common.ProblemStage;
import ru.vkurov.sonetrack.common.RequestStatus;
import ru.vkurov.sonetrack.data.entity.HistoryChangeEntity;
import ru.vkurov.sonetrack.data.entity.ProblemEntity;
import ru.vkurov.sonetrack.data.entity.UserEntity;
import ru.vkurov.sonetrack.service.ProblemService;
import ru.vkurov.sonetrack.service.impl.internal.HistoryServiceInternal;
import ru.vkurov.sonetrack.service.impl.internal.ProblemServiceInternal;
import ru.vkurov.sonetrack.service.impl.internal.RequestServiceInternal;
import ru.vkurov.sonetrack.service.impl.internal.UserServiceInternal;
import ru.vkurov.sonetrack.web.dto.problem.ProblemCreateRequest;
import ru.vkurov.sonetrack.web.dto.problem.ProblemFilter;
import ru.vkurov.sonetrack.web.dto.problem.ProblemUpdateRequest;

import static ru.vkurov.sonetrack.data.spec.ProblemSpecification.*;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {
    private final ProblemServiceInternal problemServiceInternal;
    private final UserServiceInternal userServiceInternal;
    private final RequestServiceInternal requestServiceInternal;
    private final HistoryServiceInternal historyServiceInternal;

    @Override
    @Transactional(readOnly = true)
    public ProblemEntity findById(String prefix, Long id) {
        return problemServiceInternal.findById(prefix, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProblemEntity> findProblems(ProblemFilter filter) {
        var spec = buildSpec(filter);
        return problemServiceInternal.findAll(spec);
    }

    @Override
    @Transactional
    public ProblemEntity createProblem(ProblemEntity problem, ProblemCreateRequest request) {
        fillProblem(problem, request);
        requestServiceInternal.updateRequestStatus(problem.getRequest(), RequestStatus.IN_WORK);

        return problemServiceInternal.save(problem);
    }

    @Override
    @Transactional
    public ProblemEntity updateProblem(String prefix, Long id, ProblemUpdateRequest request) {
        var problem = problemServiceInternal.findById(prefix, id);
        var historyChanges = new HistoryChangeEntity();

        if (Objects.nonNull(request.getDescription()) &&
                !Objects.equals(problem.getDescription(), request.getDescription())) {
            problem.setDescription(request.getDescription());
        }
        if (Objects.nonNull(request.getName()) && !Objects.equals(problem.getName(), request.getName())) {
            historyChanges.setOldName(problem.getName());
            historyChanges.setNewName(request.getName());
            problem.setName(request.getName());
        }
        if (Objects.nonNull(request.getStatus()) && !Objects.equals(problem.getStatus(), request.getStatus())) {
            historyChanges.setOldStatus(problem.getStatus());
            historyChanges.setNewStatus(request.getStatus());
            problem.setStatus(request.getStatus());
        }
        changeExecutor(request, problem, historyChanges);
        if (Objects.nonNull(request.getStage()) && !Objects.equals(request.getStage(), problem.getStage())) {
            historyChanges.setOldStage(problem.getStage());
            historyChanges.setNewStage(request.getStage());
            problem.setStage(request.getStage());

            updateProblemRequestStatus(problem);
        }
        var user = userServiceInternal.findCurrentUser();
        historyServiceInternal.save(historyChanges, problem, user);
        return problemServiceInternal.save(problem);
    }

    private void updateProblemRequestStatus(ProblemEntity problem) {
        //TODO: проверить, есть ли необходимость явным образом изменять статус.
        // Или достаточно засетить поле, и после сохранения problem появятся изменения и у request?
        if (Objects.equals(problem.getStage(), ProblemStage.COMPLETED)) {
            requestServiceInternal.updateRequestStatus(problem.getRequest(), RequestStatus.COMPLETED);
        } else if (Objects.equals(problem.getRequest().getStatus(), RequestStatus.COMPLETED)) {
            requestServiceInternal.updateRequestStatus(problem.getRequest(), RequestStatus.IN_WORK);
        }
    }

    private void changeExecutor(
        ProblemUpdateRequest request, ProblemEntity problem, HistoryChangeEntity historyChanges
    ) {
        if (executorChange(request)) {
            if (userServiceInternal.existsUserById(request.getUserId())) {
                var executor = userServiceInternal.findById(request.getUserId());
                historyChanges.setOldExecutor(problem.getExecutor());
                historyChanges.setNewExecutor(executor);
                problem.setExecutor(executor);
            } else {
                historyChanges.setOldExecutor(problem.getExecutor());
                historyChanges.setNewExecutor(null);
                problem.setExecutor(null);
            }
        }
    }

    private boolean executorChange(ProblemUpdateRequest request) {
        return Objects.nonNull(request.getUserId());
    }

    private void fillProblem(ProblemEntity problem, ProblemCreateRequest request) {
        var author = userServiceInternal.findCurrentUser();
        UserEntity executor = null;
        if (Objects.nonNull(request.getExecutorId())) {
            executor = userServiceInternal.findById(request.getExecutorId());
        }
        problem.setAuthor(author);
        problem.setExecutor(executor);
        var requestEntity = requestServiceInternal.findById(request.getRequestId());
        problem.setRequest(requestEntity);
        problem.setSourceType(requestEntity.getSource());
        problem.setCreatedDate(LocalDateTime.now());
        problem.setStage(ProblemStage.QUEUE);
    }

    private Specification<ProblemEntity> buildSpec(ProblemFilter filter) {
        var spec = all();

        if (Objects.nonNull(filter.getPrefix())) {
            spec = spec.and(hasPrefix(filter.getPrefix()));
        }
        if (Objects.nonNull(filter.getAfter())) {
            spec = spec.and(hasCreationDateAfter(LocalDateTime.of(filter.getAfter(), LocalTime.MIN)));
        }
        if (Objects.nonNull(filter.getAuthorId())) {
            var author = userServiceInternal.findById(filter.getAuthorId());
            spec = spec.and(hasAuthor(author));
        }
        if (Objects.nonNull(filter.getBefore())) {
            spec = spec.and(hasCreationDateBefore(LocalDateTime.of(filter.getBefore(), LocalTime.MAX)));
        }
        if (Objects.nonNull(filter.getExecutorId())) {
            if (userServiceInternal.existsUserById(filter.getExecutorId())) {
                var executor = userServiceInternal.findById(filter.getExecutorId());
                spec = spec.and(hasExecutor(executor));
            } else {
                spec = spec.and(nullExecutor());
            }
        }
        if (Objects.nonNull(filter.getName())) {
            spec = spec.and(hasName(filter.getName()));
        }
        if (Objects.nonNull(filter.getSources()) && !filter.getSources().isEmpty()) {
            spec = spec.and(hasSources(filter.getSources()));
        }
        if (Objects.nonNull(filter.getStatuses()) && !filter.getStatuses().isEmpty()) {
            spec = spec.and(hasStatuses(filter.getStatuses()));
        }
        if (Objects.nonNull(filter.getStages()) && !filter.getStages().isEmpty()) {
            spec = spec.and(hasStages(filter.getStages()));
        }

        return spec;
    }


}
