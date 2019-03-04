package ru.vkurov.sonetrack.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.vkurov.sonetrack.common.RoleName;

@Entity
@Table(name = "app_role")
@Getter
@Setter
@EqualsAndHashCode(of = "roleId")
@ToString(of = "roleName")
public class RoleEntity {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long roleId;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
