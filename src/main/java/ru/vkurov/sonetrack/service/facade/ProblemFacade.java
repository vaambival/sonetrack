package ru.vkurov.sonetrack.service.facade;

import java.util.List;
import ru.vkurov.sonetrack.web.dto.problem.ProblemCreateRequest;
import ru.vkurov.sonetrack.web.dto.problem.ProblemDto;
import ru.vkurov.sonetrack.web.dto.problem.ProblemFilter;
import ru.vkurov.sonetrack.web.dto.problem.ProblemUpdateRequest;

public interface ProblemFacade {
    ProblemDto getProblemById(String prefix, Long id);

    List<ProblemDto> findProblems(ProblemFilter filter);

    ProblemDto createProblem(ProblemCreateRequest request);

    ProblemDto updateProblem(String prefix, Long id, ProblemUpdateRequest request);
}
