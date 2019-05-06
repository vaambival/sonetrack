package ru.vkurov.sonetrack.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vkurov.sonetrack.data.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
