package ru.vkurov.sonetrack.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;


@Configuration
@EnableJdbcHttpSession(tableName = "USER_SESSION",
        maxInactiveIntervalInSeconds = HttpSessionConfig.MAX_INACTIVE_INTERVAL_IN_SECONDS)
public class HttpSessionConfig {
    //TODO: вынести в конфиги
    static final int MAX_INACTIVE_INTERVAL_IN_SECONDS = 3600; //1 hour
    private static final int SESSION_COOKIE_MAX_AGE_IN_SECONDS = 3600 * 24 * 7; // 1 week
    
    @Bean
    public HttpSessionIdResolver httpSessionStrategy() {
        var httpSessionStrategy = new CookieHttpSessionIdResolver();
        httpSessionStrategy.setCookieSerializer(cookieSerializer());
        return httpSessionStrategy;
    }
    
    @Bean
    public CookieSerializer cookieSerializer() {
        var cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieMaxAge(SESSION_COOKIE_MAX_AGE_IN_SECONDS);
        cookieSerializer.setCookieName("JSESSIONID");
        cookieSerializer.setCookiePath("/");
        cookieSerializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        cookieSerializer.setUseHttpOnlyCookie(false);
        return cookieSerializer;
    }
}
