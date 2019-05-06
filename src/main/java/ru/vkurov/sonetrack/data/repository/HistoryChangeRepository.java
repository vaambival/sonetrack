package ru.vkurov.sonetrack.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vkurov.sonetrack.data.entity.HistoryChangeEntity;

public interface HistoryChangeRepository extends JpaRepository<HistoryChangeEntity, Long> {
}
