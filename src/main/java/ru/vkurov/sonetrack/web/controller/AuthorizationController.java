package ru.vkurov.sonetrack.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import ru.vkurov.sonetrack.service.AuthenticationService;
import ru.vkurov.sonetrack.web.constants.Urls;
import ru.vkurov.sonetrack.web.dto.user.AuthenticationRequest;

@RestController
@RequestMapping(Urls.Authorization.FULL)
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public void login(@RequestBody AuthenticationRequest authenticationRequest,
                         HttpServletRequest request, HttpServletResponse response) {
        var userName = authenticationRequest.getUsername();
        var password = authenticationRequest.getPassword();
        authenticationService.authenticate(userName, password);
        
        request.getSession().setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @DeleteMapping
    public void logout(HttpSession httpSession, HttpServletResponse response) {
        httpSession.invalidate();
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
