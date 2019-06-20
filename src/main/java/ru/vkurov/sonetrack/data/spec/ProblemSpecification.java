package ru.vkurov.sonetrack.data.spec;

import java.time.LocalDateTime;
import java.util.EnumSet;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.vkurov.sonetrack.common.ProblemStage;
import ru.vkurov.sonetrack.common.ProblemStatus;
import ru.vkurov.sonetrack.common.SourceType;
import ru.vkurov.sonetrack.data.entity.ProblemEntity;
import ru.vkurov.sonetrack.data.entity.UserEntity;

@UtilityClass
public class ProblemSpecification {
    public static Specification<ProblemEntity> hasPrefix(String prefix) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("prefix"), prefix);
    }

    public static Specification<ProblemEntity> all() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(Boolean.TRUE));
    }

    public static Specification<ProblemEntity> hasCreationDateAfter(LocalDateTime date) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), date);
    }

    public static Specification<ProblemEntity> hasAuthor(UserEntity author) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("author"), author);
    }

    public static Specification<ProblemEntity> hasCreationDateBefore(LocalDateTime date) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), date);
    }

    public static Specification<ProblemEntity> hasExecutor(UserEntity executor) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("executor"), executor);
    }

    public static Specification<ProblemEntity> hasName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<ProblemEntity> hasSources(EnumSet<SourceType> sources) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.in(root.get("sourceType")).value(sources);
    }

    public static Specification<ProblemEntity> hasStatuses(EnumSet<ProblemStatus> statuses) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.in(root.get("status")).value(statuses);
    }

    public static Specification<ProblemEntity> hasStages(EnumSet<ProblemStage> stages) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.in(root.get("stage")).value(stages);
    }

    public static Specification<ProblemEntity> nullExecutor() {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.isNull(root.get("executor"));
    }
}
