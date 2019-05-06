package ru.vkurov.sonetrack.service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("scheduler")
public class SchedulerSettings {
    private IgnoreRequest ignoreRequest;

    @Getter
    @Setter
    public static class IgnoreRequest {
        private String agePeriod;
    }
}
