package ru.vkurov.sonetrack.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vkurov.sonetrack.data.entity.RequestEntity;

public interface RequestRepository extends JpaRepository<RequestEntity, Long>, JpaSpecificationExecutor<RequestEntity> {
}
