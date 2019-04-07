package ru.vkurov.sonetrack.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.vkurov.sonetrack.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationProvider provider;
    
    @Override
    public void authenticate(String username, String password) {
        var authentication = new UsernamePasswordAuthenticationToken(username, password);
        provider.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
