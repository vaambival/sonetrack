package ru.vkurov.sonetrack.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import ru.vkurov.sonetrack.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationProvider provider;
    private final SessionRegistry sessionRegistry;
    
    @Override
    public void authenticate(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        provider.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
