package ru.vkurov.sonetrack.service.impl;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vkurov.sonetrack.common.RequestStatus;
import ru.vkurov.sonetrack.data.entity.RequestEntity;
import ru.vkurov.sonetrack.service.RequestService;
import ru.vkurov.sonetrack.service.impl.internal.RequestServiceInternal;
import ru.vkurov.sonetrack.web.dto.request.RequestFilter;

import static ru.vkurov.sonetrack.data.spec.RequestSpecification.*;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestServiceInternal requestServiceInternal;

    @Override
    @Transactional
    public List<RequestEntity> findAll(RequestFilter filter) {
        var spec = buildSpec(filter);
        return requestServiceInternal.findAll(spec);
    }

    @Override
    @Transactional
    public void ignoreRequest(Long id) {
        var request = requestServiceInternal.findById(id);
        requestServiceInternal.updateRequestStatus(request, RequestStatus.IGNORED);
    }

    private List<RequestEntity> findNewRequestsOlderThan(Period period) {
        var mostOldDate = LocalDateTime.now().minus(period);
        var spec = olderThan(mostOldDate).and(hasStatusesIn(EnumSet.of(RequestStatus.NEW)));

        return requestServiceInternal.findAll(spec);
    }

    @Override
    public void ignoreRequestsOlderThan(Period maxAge) {
        var requests = findNewRequestsOlderThan(maxAge);
        requests.forEach(request -> request.setStatus(RequestStatus.IGNORED));
        requestServiceInternal.saveAll(requests);
    }

    private Specification<RequestEntity> buildSpec(RequestFilter filter) {
        var spec = all();

        if (Objects.nonNull(filter.getSources()) && !filter.getSources().isEmpty()) {
            spec = spec.and(hasSourcesIn(filter.getSources()));
        }
        if (Objects.nonNull(filter.getStatuses()) && !filter.getStatuses().isEmpty()) {
            spec = spec.and(hasStatusesIn(filter.getStatuses()));
        }
        if (filter.getMinCommentCount() > 0) {
            spec = spec.and(hasCommentsCountBiggerThan(filter.getMinCommentCount()));
        }
        if (filter.getMinLikeCount() > 0) {
            spec = spec.and(hasLikesCountBiggerThan(filter.getMinLikeCount()));
        }
        return spec;
    }
}
