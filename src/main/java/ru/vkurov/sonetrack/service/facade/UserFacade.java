package ru.vkurov.sonetrack.service.facade;

import java.util.List;
import ru.vkurov.sonetrack.web.dto.user.UserDto;

public interface UserFacade {
    List<UserDto> getAll();
}
