package ru.vkurov.sonetrack.service.client;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.vkurov.sonetrack.web.dto.request.RequestDto;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@RequiredArgsConstructor
public class RestClient {
    private final RestTemplate restTemplate;

    public List<RequestDto> getNewRequests(String url, String query) {
        var uri = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("query", query).build().encode(UTF_8).toUri();
        return restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<RequestDto>>() {})
            .getBody();
    }
}
