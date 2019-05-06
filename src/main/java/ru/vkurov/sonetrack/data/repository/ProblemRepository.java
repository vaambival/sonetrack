package ru.vkurov.sonetrack.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vkurov.sonetrack.data.entity.ProblemEntity;
import ru.vkurov.sonetrack.data.entity.ProblemId;

public interface ProblemRepository extends JpaRepository<ProblemEntity, ProblemId>,
        JpaSpecificationExecutor<ProblemEntity> {
}
