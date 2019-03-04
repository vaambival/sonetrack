package ru.vkurov.sonetrack.web.dto.user;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.vkurov.sonetrack.common.RoleName;

@Getter
@Setter
@Accessors(chain = true)
public class UserDto {
    private Long userId;
    private String userName;
    private String firstName;
    private String secondName;
    private String lastName;

    private String email;
    private List<RoleName> roles;
}
