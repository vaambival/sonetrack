package ru.vkurov.sonetrack.service.facade.impl;

import java.time.Period;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vkurov.sonetrack.service.RequestService;
import ru.vkurov.sonetrack.service.facade.RequestFacade;
import ru.vkurov.sonetrack.service.mapper.RequestMapper;
import ru.vkurov.sonetrack.web.dto.request.RequestDto;
import ru.vkurov.sonetrack.web.dto.request.RequestFilter;

@Component
@RequiredArgsConstructor
public class RequestFacadeImpl implements RequestFacade {
    private final RequestService requestService;
    private final RequestMapper mapper;

    @Override
    public List<RequestDto> getAll(RequestFilter filter) {
        return mapper.toDtos(requestService.findAll(filter));
    }

    @Override
    public void ignoreRequest(Long id) {
        requestService.ignoreRequest(id);
    }

    @Override
    public void ignoreRequestsOlderThan(Period maxAge) {
        requestService.ignoreRequestsOlderThan(maxAge);
    }
}
