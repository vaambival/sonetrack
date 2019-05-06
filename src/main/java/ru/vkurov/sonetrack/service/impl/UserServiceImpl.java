package ru.vkurov.sonetrack.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vkurov.sonetrack.data.entity.UserEntity;
import ru.vkurov.sonetrack.data.repository.UserRepository;
import ru.vkurov.sonetrack.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }
}
