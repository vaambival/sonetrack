package ru.vkurov.sonetrack.web.dto.request;

import java.util.EnumSet;
import lombok.Getter;
import lombok.Setter;
import ru.vkurov.sonetrack.common.RequestStatus;
import ru.vkurov.sonetrack.common.SourceType;

@Getter
@Setter
public class RequestFilter {
    private EnumSet<SourceType> sources;
    private int minLikeCount;
    private int minCommentCount;
    private EnumSet<RequestStatus> statuses;
}
