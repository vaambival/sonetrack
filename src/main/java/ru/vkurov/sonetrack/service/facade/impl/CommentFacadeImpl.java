package ru.vkurov.sonetrack.service.facade.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vkurov.sonetrack.data.entity.CommentEntity;
import ru.vkurov.sonetrack.service.CommentService;
import ru.vkurov.sonetrack.service.facade.CommentFacade;
import ru.vkurov.sonetrack.service.mapper.CommentMapper;
import ru.vkurov.sonetrack.web.dto.comment.CommentCreateRequest;
import ru.vkurov.sonetrack.web.dto.comment.CommentDto;

@Component
@RequiredArgsConstructor
public class CommentFacadeImpl implements CommentFacade {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDto> addComment(String prefix, Long id, CommentCreateRequest request) {
        var comment = new CommentEntity();
        comment.setText(request.getText());
        return commentMapper.toDtos(commentService.addCommentAndReturnWithOthers(prefix, id, comment));
    }
}
