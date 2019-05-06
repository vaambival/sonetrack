package ru.vkurov.sonetrack.web.dto.history;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.vkurov.sonetrack.common.ProblemStage;
import ru.vkurov.sonetrack.common.ProblemStatus;
import ru.vkurov.sonetrack.web.dto.user.UserDto;

@Getter
@Setter
@Accessors(chain = true)
public class HistoryChangeDto {
    @NotNull
    private Long id;

    private String oldName;
    private String newName;

    private ProblemStatus oldStatus;
    private ProblemStatus newStatus;

    private UserDto oldExecutor;
    private UserDto newExecutor;

    private ProblemStage oldStage;
    private ProblemStage newStage;
}
