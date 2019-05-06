package ru.vkurov.sonetrack.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import ru.vkurov.sonetrack.common.RoleName;
import ru.vkurov.sonetrack.data.entity.RoleEntity;
import ru.vkurov.sonetrack.service.mapper.config.DefaultMapperConfig;
import ru.vkurov.sonetrack.web.dto.role.RoleDto;

@Mapper(config = DefaultMapperConfig.class)
public interface RoleMapper {
    default RoleDto toDto(RoleEntity entity) {
        return new RoleDto()
                .setId(entity.getRoleId())
                .setName(entity.getRoleName().name());
    }

    default RoleEntity toEntity(RoleDto roleDto) {
        var role = new RoleEntity();
        role.setRoleId(roleDto.getId());
        role.setRoleName(RoleName.valueOf(roleDto.getName()));

        return role;
    }

    List<RoleDto> toDtos(List<RoleEntity> entities);
    List<RoleEntity> toEntities(List<RoleDto> dtos);
}
