package ru.vkurov.sonetrack.web.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {
    private String text;
}
