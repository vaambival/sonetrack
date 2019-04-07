package ru.vkurov.sonetrack.web.config;

import java.util.Collections;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String DEFAULT_PORT = "http://localhost:8081";

    private final UserDetailsService userDetailsService;
    private final DataSource dataSource;
    private final FindByIndexNameSessionRepository sessionRepository;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("api/v1/login").permitAll()
                    .antMatchers("/home").authenticated()
                    .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    //TODO: вынести в конфиги
                    .maximumSessions(10)
                    .maxSessionsPreventsLogin(false)
                    .sessionRegistry(sessionRegistry())
                .and()
                    .sessionFixation().migrateSession()
                .and()
                    .httpBasic()
                .and()
                    .rememberMe()
                    .tokenRepository(this.persistentTokenRepository())
                //TODO: вынести в конфиги
                    .tokenValiditySeconds(24 * 60 * 60);
        
    }

    @Bean
    public FilterRegistrationBean simpleCorsFilter() {
        var source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // *** URL below needs to match the Vue client URL and port ***
        //TODO: вынести в конфиги
        config.setAllowedOrigins(Collections.singletonList(DEFAULT_PORT));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        var filterFilterRegistrationBean = new FilterRegistrationBean<>(new CorsFilter(source));
        filterFilterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterFilterRegistrationBean;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        var jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @SuppressWarnings("unchecked")
    @Bean
    SpringSessionBackedSessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry(sessionRepository);
    }
}
