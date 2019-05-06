package ru.vkurov.sonetrack.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import ru.vkurov.sonetrack.data.entity.UserEntity;
import ru.vkurov.sonetrack.service.mapper.config.DefaultMapperConfig;
import ru.vkurov.sonetrack.web.dto.user.UserDto;

@Mapper(config = DefaultMapperConfig.class, uses = RoleMapper.class)
public interface UserMapper {
    UserDto toDto(UserEntity userEntity);
    UserEntity toEntity(UserDto userDto);

    List<UserDto> toDtos(List<UserEntity> entities);
}
