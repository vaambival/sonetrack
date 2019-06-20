package ru.vkurov.sonetrack.data.spec;

import java.time.LocalDateTime;
import java.util.EnumSet;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.vkurov.sonetrack.common.RequestStatus;
import ru.vkurov.sonetrack.common.SourceType;
import ru.vkurov.sonetrack.data.entity.RequestEntity;

@UtilityClass
public class RequestSpecification {
    public static Specification<RequestEntity> all() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(Boolean.TRUE));
    }

    public static Specification<RequestEntity> hasSourcesIn(EnumSet<SourceType> sources) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("source")).value(sources);
    }

    public static Specification<RequestEntity> hasStatusesIn(EnumSet<RequestStatus> statuses) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("status")).value(statuses);
    }

    public static Specification<RequestEntity> hasCommentsCountBiggerThan(int minCount) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(root.get("commentCount"), minCount);
    }

    public static Specification<RequestEntity> hasLikesCountBiggerThan(int minLikes) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(root.get("likeCount"), minLikes);
    }

    public static Specification<RequestEntity> olderThan(LocalDateTime dateTime) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("created"), dateTime);
    }
}
