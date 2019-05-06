package ru.vkurov.sonetrack.web.dto.problem;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.vkurov.sonetrack.common.ProblemStage;
import ru.vkurov.sonetrack.common.ProblemStatus;
import ru.vkurov.sonetrack.common.SourceType;
import ru.vkurov.sonetrack.web.dto.comment.CommentDto;
import ru.vkurov.sonetrack.web.dto.history.HistoryDto;
import ru.vkurov.sonetrack.web.dto.request.RequestDto;
import ru.vkurov.sonetrack.web.dto.user.UserDto;

@Getter
@Setter
@Accessors(chain = true)
public class ProblemDto {
    @NotNull
    private Long id;
    @NotNull
    private String prefix;
    @NotNull
    private String name;
    @NotNull
    private SourceType sourceType;
    @NotNull
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime createdDate;
    @NotNull
    private UserDto author;
    @NotNull
    private ProblemStatus status;
    private UserDto executor;
    @NotNull
    private RequestDto requestDto;
    private String description;
    private List<HistoryDto> historyDtos = new ArrayList<>();
    private List<CommentDto> comments = new ArrayList<>();
    @NotNull
    private ProblemStage stage;
}
