package ru.vkurov.sonetrack.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vkurov.sonetrack.service.facade.ProblemFacade;
import ru.vkurov.sonetrack.web.constants.Urls;
import ru.vkurov.sonetrack.web.dto.problem.ProblemCreateRequest;
import ru.vkurov.sonetrack.web.dto.problem.ProblemDto;
import ru.vkurov.sonetrack.web.dto.problem.ProblemFilter;
import ru.vkurov.sonetrack.web.dto.problem.ProblemUpdateRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping(Urls.Problem.FULL)
public class ProblemController {
    private final ProblemFacade problemFacade;

    @GetMapping(Urls.Problem.ById.FULL)
    public ProblemDto getProblemById(@PathVariable("prefix") String prefix,
                                     @PathVariable("id") Long id) {
        return problemFacade.getProblemById(prefix, id);
    }

    //TODO: добавить пагинацию
    @GetMapping
    public List<ProblemDto> getAll(ProblemFilter filter) {
        return problemFacade.findProblems(filter);
    }

    @PostMapping
    public ProblemDto createProblem(@RequestBody ProblemCreateRequest request) {
        return problemFacade.createProblem(request);
    }

    @PatchMapping(Urls.Problem.ById.FULL)
    public ProblemDto updateProblem(@PathVariable("prefix") String prefix,
                                    @PathVariable("id") Long id,
                                    @RequestBody ProblemUpdateRequest request) {
        return problemFacade.updateProblem(prefix, id, request);
    }
}
