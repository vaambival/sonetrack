package ru.vkurov.sonetrack.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vkurov.sonetrack.data.entity.ProblemEntity;
import ru.vkurov.sonetrack.service.mapper.config.DefaultMapperConfig;
import ru.vkurov.sonetrack.web.dto.problem.ProblemCreateRequest;
import ru.vkurov.sonetrack.web.dto.problem.ProblemDto;

@Mapper(config = DefaultMapperConfig.class,
    uses = {UserMapper.class, RequestMapper.class, HistoryMapper.class, CommentMapper.class})
public interface ProblemMapper {
    @Mapping(source = "request", target = "requestDto")
    @Mapping(source = "history", target = "historyDtos")
    ProblemDto toDto(ProblemEntity problemEntity);

    @Mapping(source = "requestDto", target = "request")
    ProblemEntity toEntity(ProblemDto problemDto);
    List<ProblemDto> toDtos(List<ProblemEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "executor", ignore = true)
    @Mapping(target = "request", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "sourceType", ignore = true)
    @Mapping(target = "history", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "stage", ignore = true)
    ProblemEntity toEntity(ProblemCreateRequest request);
}
