package ru.vkurov.sonetrack.web.dto.history;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.vkurov.sonetrack.web.dto.user.UserDto;

@Getter
@Setter
@Accessors(chain = true)
public class HistoryDto {
    @NotNull
    private Long id;
    @NotNull
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime operationDate;
    @NotNull
    private HistoryChangeDto historyChange;
    @NotNull
    private UserDto author;
}
