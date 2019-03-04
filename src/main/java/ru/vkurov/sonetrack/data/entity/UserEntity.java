package ru.vkurov.sonetrack.data.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@EqualsAndHashCode(of = "userId")
@ToString(of = {"userName", "email"})
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    private String userName;
    private String firstName;
    private String secondName;
    private String lastName;

    private String email;
    private String encrytedPassword;
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private List<RoleEntity> roles;
}
