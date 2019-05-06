package ru.vkurov.sonetrack.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vkurov.sonetrack.data.entity.HistoryEntity;
import ru.vkurov.sonetrack.service.mapper.config.DefaultMapperConfig;
import ru.vkurov.sonetrack.web.dto.history.HistoryDto;

@Mapper(config = DefaultMapperConfig.class, uses = {HistoryChangeMapper.class, UserMapper.class})
public interface HistoryMapper {
    HistoryDto toDto(HistoryEntity entity);
    List<HistoryDto> toDtos(List<HistoryEntity> entities);
}
