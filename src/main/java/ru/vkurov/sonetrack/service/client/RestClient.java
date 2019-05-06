package ru.vkurov.sonetrack.service.client;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.vkurov.sonetrack.web.dto.request.RequestDto;

@Component
@RequiredArgsConstructor
public class RestClient {
    private final RestTemplate restTemplate;

    public List<RequestDto> getNewRequests(String uri) {
        return restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<RequestDto>>() {})
            .getBody();
    }
}
