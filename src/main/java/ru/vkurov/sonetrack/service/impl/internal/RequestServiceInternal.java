package ru.vkurov.sonetrack.service.impl.internal;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.vkurov.sonetrack.common.RequestStatus;
import ru.vkurov.sonetrack.data.entity.RequestEntity;
import ru.vkurov.sonetrack.data.repository.RequestRepository;
import ru.vkurov.sonetrack.service.exception.NotFoundException;

@Component
@RequiredArgsConstructor
public class RequestServiceInternal {
    private final RequestRepository requestRepository;

    public RequestEntity findById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> {
                    var field = Collections.singletonMap("id", String.valueOf(id));
                    return new NotFoundException(RequestEntity.class.getSimpleName(), field);
                });
    }

    public RequestEntity updateRequestStatus(RequestEntity request, RequestStatus status) {
        request.setStatus(status);
        return requestRepository.save(request);
    }

    public List<RequestEntity> findAll(Specification<RequestEntity> spec) {
        return requestRepository.findAll(spec);
    }

    public List<RequestEntity> saveAll(List<RequestEntity> requests) {
        return requestRepository.saveAll(requests);
    }
}
