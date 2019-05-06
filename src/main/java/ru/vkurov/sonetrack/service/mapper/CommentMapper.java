package ru.vkurov.sonetrack.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import ru.vkurov.sonetrack.data.entity.CommentEntity;
import ru.vkurov.sonetrack.service.mapper.config.DefaultMapperConfig;
import ru.vkurov.sonetrack.web.dto.comment.CommentDto;

@Mapper(config = DefaultMapperConfig.class)
public interface CommentMapper {
    CommentDto toDto(CommentEntity entity);
    List<CommentDto> toDtos(List<CommentEntity> commentEntities);
}
