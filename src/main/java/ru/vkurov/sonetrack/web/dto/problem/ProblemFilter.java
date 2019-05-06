package ru.vkurov.sonetrack.web.dto.problem;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.EnumSet;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.vkurov.sonetrack.common.ProblemStage;
import ru.vkurov.sonetrack.common.ProblemStatus;
import ru.vkurov.sonetrack.common.SourceType;

@Getter
@Setter
public class ProblemFilter {
    @NotNull
    private String prefix;
    private String name;
    private EnumSet<SourceType> sources;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate before;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate after;
    private Long authorId;
    private EnumSet<ProblemStatus> statuses;
    private Long executorId;
    private EnumSet<ProblemStage> stages;
}
