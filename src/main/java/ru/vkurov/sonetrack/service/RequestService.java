package ru.vkurov.sonetrack.service;

import java.time.Period;
import java.util.List;
import ru.vkurov.sonetrack.data.entity.RequestEntity;
import ru.vkurov.sonetrack.web.dto.request.RequestFilter;

public interface RequestService {

    List<RequestEntity> findAll(RequestFilter filter);

    void ignoreRequest(Long id);

    void ignoreRequestsOlderThan(Period maxAge);
}
