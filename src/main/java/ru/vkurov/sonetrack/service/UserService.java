package ru.vkurov.sonetrack.service;

import java.util.List;
import ru.vkurov.sonetrack.data.entity.UserEntity;

public interface UserService {
    List<UserEntity> getAll();
}
