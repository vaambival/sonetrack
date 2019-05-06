package ru.vkurov.sonetrack.service.facade.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vkurov.sonetrack.service.UserService;
import ru.vkurov.sonetrack.service.facade.UserFacade;
import ru.vkurov.sonetrack.service.mapper.UserMapper;
import ru.vkurov.sonetrack.web.dto.user.UserDto;

@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        return userMapper.toDtos(userService.getAll());
    }
}
