package ru.vkurov.sonetrack.service.impl.internal;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vkurov.sonetrack.data.entity.CommentEntity;
import ru.vkurov.sonetrack.data.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class CommentServiceInternal {
    private final CommentRepository commentRepository;

    public CommentEntity saveComment(CommentEntity comment) {
        return commentRepository.saveAndFlush(comment);
    }
}
