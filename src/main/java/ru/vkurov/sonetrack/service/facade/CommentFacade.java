package ru.vkurov.sonetrack.service.facade;

import java.util.List;
import ru.vkurov.sonetrack.web.dto.comment.CommentCreateRequest;
import ru.vkurov.sonetrack.web.dto.comment.CommentDto;

public interface CommentFacade {
    List<CommentDto> addComment(String prefix, Long id, CommentCreateRequest request);
}
