package ru.vkurov.sonetrack.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import ru.vkurov.sonetrack.data.entity.RequestEntity;
import ru.vkurov.sonetrack.service.mapper.config.DefaultMapperConfig;
import ru.vkurov.sonetrack.web.dto.request.RequestDto;

@Mapper(config = DefaultMapperConfig.class)
public interface RequestMapper {
    RequestDto toDto(RequestEntity entity);
    RequestEntity toEntity(RequestDto dto);

    List<RequestDto> toDtos(List<RequestEntity> entities);
    List<RequestEntity> toEntities(List<RequestDto> dtos);
}
