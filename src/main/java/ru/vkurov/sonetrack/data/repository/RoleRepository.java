package ru.vkurov.sonetrack.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vkurov.sonetrack.data.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
