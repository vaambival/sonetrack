package ru.vkurov.sonetrack.service.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vkurov.sonetrack.data.entity.RoleEntity;
import ru.vkurov.sonetrack.data.entity.UserEntity;
import ru.vkurov.sonetrack.data.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("Load UserEntity");

        UserEntity user = userRepository.findUserByUserName(userName);
        
        if (user == null) {
            log.info("UserEntity was not found");
            throw new UsernameNotFoundException("No user with name: " + userName);
        }
        
        log.info("UserEntity is successfully loaded");
    
        List<RoleEntity> roles = userRepository.findUserRoles(userName);
        List<GrantedAuthority> grants = new ArrayList<>();
        if (roles != null) {
            roles.forEach(role -> grants.add(new SimpleGrantedAuthority(role.getRoleName().name())));
        }
        
        return new User(userName, user.getEncrytedPassword(), grants);
    }
}
