package ru.vkurov.sonetrack.service;

import java.util.List;
import ru.vkurov.sonetrack.data.entity.ProblemEntity;
import ru.vkurov.sonetrack.web.dto.problem.ProblemCreateRequest;
import ru.vkurov.sonetrack.web.dto.problem.ProblemFilter;
import ru.vkurov.sonetrack.web.dto.problem.ProblemUpdateRequest;

public interface ProblemService {
    ProblemEntity findById(String prefix, Long id);

    List<ProblemEntity> findProblems(ProblemFilter filter);

    ProblemEntity createProblem(ProblemEntity problem, ProblemCreateRequest request);

    ProblemEntity updateProblem(String prefix, Long id, ProblemUpdateRequest request);
}
