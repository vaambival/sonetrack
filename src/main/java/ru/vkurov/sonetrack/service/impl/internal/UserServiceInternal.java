package ru.vkurov.sonetrack.service.impl.internal;

import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.vkurov.sonetrack.data.entity.UserEntity;
import ru.vkurov.sonetrack.data.repository.UserRepository;
import ru.vkurov.sonetrack.service.exception.NotFoundException;
import ru.vkurov.sonetrack.service.exception.UnauthorizedException;

@Component
@RequiredArgsConstructor
public class UserServiceInternal {
    private final UserRepository userRepository;

    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    var field = Collections.singletonMap("id", String.valueOf(id));
                    return new NotFoundException(UserEntity.class.getSimpleName(), field);
                });
    }

    public boolean existsUserById(Long id) {
        return userRepository.findById(id).isPresent();
    }

    public UserEntity findCurrentUser() {
        String username = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(UserDetails.class::cast)
                .map(UserDetails::getUsername)
                .orElseThrow(UnauthorizedException::new);

        return userRepository.findByUserName(username);
    }
}
