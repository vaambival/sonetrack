package ru.vkurov.sonetrack.service.facade.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vkurov.sonetrack.service.ProblemService;
import ru.vkurov.sonetrack.service.facade.ProblemFacade;
import ru.vkurov.sonetrack.service.mapper.ProblemMapper;
import ru.vkurov.sonetrack.web.dto.problem.ProblemCreateRequest;
import ru.vkurov.sonetrack.web.dto.problem.ProblemDto;
import ru.vkurov.sonetrack.web.dto.problem.ProblemFilter;
import ru.vkurov.sonetrack.web.dto.problem.ProblemUpdateRequest;

@Component
@RequiredArgsConstructor
public class ProblemFacadeImpl implements ProblemFacade {
    private final ProblemService problemService;
    private final ProblemMapper problemMapper;

    @Override
    public ProblemDto getProblemById(String prefix, Long id) {
        return problemMapper.toDto(problemService.findById(prefix, id));
    }

    @Override
    public List<ProblemDto> findProblems(ProblemFilter filter) {
        return problemMapper.toDtos(problemService.findProblems(filter));
    }

    @Override
    public ProblemDto createProblem(ProblemCreateRequest request) {
        var problemEntity = problemMapper.toEntity(request);
        return problemMapper.toDto(
                problemService.createProblem(problemEntity, request));
    }

    @Override
    public ProblemDto updateProblem(String prefix, Long id, ProblemUpdateRequest request) {
        return problemMapper.toDto(problemService.updateProblem(prefix, id, request));
    }
}
