package ru.vkurov.sonetrack.service.impl.internal;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vkurov.sonetrack.data.entity.HistoryChangeEntity;
import ru.vkurov.sonetrack.data.entity.HistoryEntity;
import ru.vkurov.sonetrack.data.entity.ProblemEntity;
import ru.vkurov.sonetrack.data.entity.UserEntity;
import ru.vkurov.sonetrack.data.repository.HistoryChangeRepository;
import ru.vkurov.sonetrack.data.repository.HistoryRepository;

@Component
@RequiredArgsConstructor
public class HistoryServiceInternal {
    private final HistoryRepository historyRepository;
    private final HistoryChangeRepository historyChangeRepository;

    public void save(HistoryChangeEntity historyChanges, ProblemEntity problemEntity, UserEntity userEntity) {
        if (isChanged(historyChanges)) {
            historyChangeRepository.saveAndFlush(historyChanges);
            var history = new HistoryEntity();
            history.setHistoryChange(historyChanges);
            history.setOperationDate(LocalDateTime.now());
            history.setProblem(problemEntity);
            history.setAuthor(userEntity);
            historyRepository.saveAndFlush(history);
        }
    }

    private boolean isChanged(HistoryChangeEntity historyChanges) {
        if (isChangedExecutor(historyChanges)) return true;
        if (Objects.nonNull(historyChanges.getNewName())) return true;
        if (Objects.nonNull(historyChanges.getNewStatus())) return true;
        if (Objects.nonNull(historyChanges.getNewStage())) return true;
        return Objects.nonNull(historyChanges.getNewStatus());
    }

    private boolean isChangedExecutor(HistoryChangeEntity historyChanges) {
        return Objects.nonNull(historyChanges.getNewExecutor()) ||
            Objects.nonNull(historyChanges.getOldExecutor());
    }
}
