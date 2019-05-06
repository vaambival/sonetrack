package ru.vkurov.sonetrack.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vkurov.sonetrack.service.facade.FetchNewRequestFacade;

@Component
@RequiredArgsConstructor
@Slf4j
public class FetchNewRequestJob {
    private final FetchNewRequestFacade fetchNewRequestFacade;

    @Scheduled(fixedRateString = "${scheduler.fetch-new-request.fixed-rate-in-ms}")
    public void fetchNewRequest() {
        log.info("Fetch From Core Start");
        fetchNewRequestFacade.fetchNewRequests();
    }
}
