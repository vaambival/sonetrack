package ru.vkurov.sonetrack.service.config;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("converter")
public class SoneTrackConverterSettings {
    private Url url;
    private List<String> queries = new ArrayList<>();

    @Getter
    @Setter
    public static class Url {
        private String trackUrl;
    }
}
