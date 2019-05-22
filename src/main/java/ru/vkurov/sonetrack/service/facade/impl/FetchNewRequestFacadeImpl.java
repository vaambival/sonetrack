package ru.vkurov.sonetrack.service.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vkurov.sonetrack.common.RequestStatus;
import ru.vkurov.sonetrack.service.client.RestClient;
import ru.vkurov.sonetrack.service.config.SoneTrackConverterSettings;
import ru.vkurov.sonetrack.service.facade.FetchNewRequestFacade;
import ru.vkurov.sonetrack.service.impl.internal.RequestServiceInternal;
import ru.vkurov.sonetrack.service.mapper.RequestMapper;

@Component
@RequiredArgsConstructor
public class FetchNewRequestFacadeImpl implements FetchNewRequestFacade {
    private final SoneTrackConverterSettings soneTrackConverterSettings;
    private final RestClient restClient;
    private final RequestMapper requestMapper;
    private final RequestServiceInternal requestService;


    @Override
    public void fetchNewRequests() {
        for (var query : soneTrackConverterSettings.getQueries()) {
            var requestDtos = restClient.getNewRequests(soneTrackConverterSettings.getUrl().getTrackUrl(), query);
            requestDtos.forEach(requestDto -> requestDto.setStatus(RequestStatus.NEW));
            var requests = requestMapper.toEntities(requestDtos);
            requestService.saveAll(requests);
        }
    }
}
