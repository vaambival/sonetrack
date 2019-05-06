package ru.vkurov.sonetrack.service.impl.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.vkurov.sonetrack.data.entity.ProblemEntity;
import ru.vkurov.sonetrack.data.entity.ProblemId;
import ru.vkurov.sonetrack.data.repository.ProblemRepository;
import ru.vkurov.sonetrack.service.exception.NotFoundException;

@Component
@RequiredArgsConstructor
public class ProblemServiceInternal {
    private final ProblemRepository problemRepository;

    public ProblemEntity findById(String prefix, Long id) {
        return problemRepository.findById(new ProblemId(id, prefix))
                .orElseThrow(() -> {
                    Map<String, String> fields = new HashMap<>();
                    fields.put("prefix", prefix);
                    fields.put("id", String.valueOf(id));
                    return new NotFoundException(ProblemEntity.class.getSimpleName(), fields);
                });
    }

    public List<ProblemEntity> findAll(Specification<ProblemEntity> spec) {
        return problemRepository.findAll(spec);
    }

    public ProblemEntity save(ProblemEntity problem) {
        return problemRepository.saveAndFlush(problem);
    }
}
