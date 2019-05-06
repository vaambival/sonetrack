package ru.vkurov.sonetrack.web.dto.role;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RoleDto {
    @NotNull
    private Long id;
    @NotNull
    private String name;
}
