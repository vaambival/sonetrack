package ru.vkurov.sonetrack.web.dto.problem;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.vkurov.sonetrack.common.ProblemStatus;

@Getter
@Setter
@Accessors(chain = true)
public class ProblemCreateRequest {
    @NotNull
    private String prefix;
    @NotNull
    private String name;
    @NotNull
    private ProblemStatus status;
    private Long executorId;
    @NotNull
    private Long requestId;
    private String description;
}
