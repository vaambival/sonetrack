package ru.vkurov.sonetrack.web.dto.request;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.vkurov.sonetrack.common.RequestStatus;
import ru.vkurov.sonetrack.common.SourceType;

@Getter
@Setter
@Accessors(chain = true)
public class RequestDto {
    @NotNull
    private Long id;
    private String description;
    @NotNull
    private SourceType source;
    private int likeCount;
    private int commentCount;
    @NotNull
    private String link;
    @NotNull
    private RequestStatus status;
    @NotNull
    private LocalDateTime created;
}
