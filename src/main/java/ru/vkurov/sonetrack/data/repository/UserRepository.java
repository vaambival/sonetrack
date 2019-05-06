package ru.vkurov.sonetrack.data.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vkurov.sonetrack.data.entity.RoleEntity;
import ru.vkurov.sonetrack.data.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);

    @Query("Select roles from UserEntity user JOIN user.roles roles WHERE user.userName = :userName")
    List<RoleEntity> findUserRoles(@Param("userName") String userName);
}
