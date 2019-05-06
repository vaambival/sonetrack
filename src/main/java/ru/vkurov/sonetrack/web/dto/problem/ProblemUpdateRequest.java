package ru.vkurov.sonetrack.web.dto.problem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.vkurov.sonetrack.common.ProblemStage;
import ru.vkurov.sonetrack.common.ProblemStatus;

@Getter
@Setter
@Accessors(chain = true)
public class ProblemUpdateRequest {
    private String name;
    private ProblemStatus status;
    private Long userId;
    private String description;
    private ProblemStage stage;
}
