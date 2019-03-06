package ru.vkurov.sonetrack.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionStrategy;


@Configuration
@EnableJdbcHttpSession(tableName = "USER_SESSION",
        maxInactiveIntervalInSeconds = HttpSessionConfig.MAX_INACTIVE_INTERVAL_IN_SECONDS)
public class HttpSessionConfig {
    
    static final int MAX_INACTIVE_INTERVAL_IN_SECONDS = 3600; //1 hour
    private static final int SESSION_COOKIE_MAX_AGE_IN_SECONDS = 3600 * 24 * 7; // 1 week
    
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        CookieHttpSessionStrategy httpSessionStrategy = new CookieHttpSessionStrategy();
        httpSessionStrategy.setCookieSerializer(cookieSerializer());
        return httpSessionStrategy;
    }
    
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieMaxAge(SESSION_COOKIE_MAX_AGE_IN_SECONDS);
        return cookieSerializer;
    }
}