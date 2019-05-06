package ru.vkurov.sonetrack.scheduler;

import java.time.Period;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vkurov.sonetrack.common.RequestStatus;
import ru.vkurov.sonetrack.service.config.SchedulerSettings;
import ru.vkurov.sonetrack.service.facade.RequestFacade;

@Component
@RequiredArgsConstructor
@Slf4j
public class IgnoreOldNewRequestJob {
    private final RequestFacade requestFacade;
    private final SchedulerSettings settings;

    @Scheduled(fixedRateString = "${scheduler.ignore-request.fixed-rate-in-ms}")
    public void ignoreRequests() {
        log.info("Ignoring requests");
        var maxAge = Period.parse(settings.getIgnoreRequest().getAgePeriod());
        requestFacade.ignoreRequestsOlderThan(maxAge);
    }
}
