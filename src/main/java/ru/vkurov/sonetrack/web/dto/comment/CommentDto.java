package ru.vkurov.sonetrack.web.dto.comment;

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
public class CommentDto {
    @NotNull
    private Long id;
    @NotNull
    private UserDto author;
    @NotNull
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime created;
    @NotNull
    private String text;
}
