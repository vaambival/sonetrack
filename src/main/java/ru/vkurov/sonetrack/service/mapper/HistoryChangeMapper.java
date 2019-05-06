package ru.vkurov.sonetrack.service.mapper;

import org.mapstruct.Mapper;
import ru.vkurov.sonetrack.data.entity.HistoryChangeEntity;
import ru.vkurov.sonetrack.service.mapper.config.DefaultMapperConfig;
import ru.vkurov.sonetrack.web.dto.history.HistoryChangeDto;

@Mapper(config = DefaultMapperConfig.class)
public interface HistoryChangeMapper {
    HistoryChangeDto toDto(HistoryChangeEntity entity);
}
