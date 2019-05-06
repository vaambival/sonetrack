package ru.vkurov.sonetrack.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vkurov.sonetrack.data.entity.HistoryEntity;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
}
