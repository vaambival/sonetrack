package ru.vkurov.sonetrack.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vkurov.sonetrack.service.facade.UserFacade;
import ru.vkurov.sonetrack.web.constants.Urls;
import ru.vkurov.sonetrack.web.dto.user.UserDto;

@RestController
@RequiredArgsConstructor
@RequestMapping(Urls.User.FULL)
public class UserController {
    private final UserFacade userFacade;

    @GetMapping
    public List<UserDto> getAll() {
        return userFacade.getAll();
    }
}
