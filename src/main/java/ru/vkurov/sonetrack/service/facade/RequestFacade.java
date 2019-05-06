package ru.vkurov.sonetrack.service.facade;

import java.time.Period;
import java.util.List;
import ru.vkurov.sonetrack.web.dto.request.RequestDto;
import ru.vkurov.sonetrack.web.dto.request.RequestFilter;

public interface RequestFacade {
    List<RequestDto> getAll(RequestFilter filter);

    void ignoreRequest(Long id);

    void ignoreRequestsOlderThan(Period maxAge);
}
