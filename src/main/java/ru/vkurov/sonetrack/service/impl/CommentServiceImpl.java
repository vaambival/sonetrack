package ru.vkurov.sonetrack.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vkurov.sonetrack.data.entity.CommentEntity;
import ru.vkurov.sonetrack.service.CommentService;
import ru.vkurov.sonetrack.service.impl.internal.CommentServiceInternal;
import ru.vkurov.sonetrack.service.impl.internal.ProblemServiceInternal;
import ru.vkurov.sonetrack.service.impl.internal.UserServiceInternal;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentServiceInternal commentServiceInternal;
    private final UserServiceInternal userServiceInternal;
    private final ProblemServiceInternal problemServiceInternal;

    @Override
    @Transactional
    public List<CommentEntity> addCommentAndReturnWithOthers(String prefix, Long id, CommentEntity comment) {
        var problem = problemServiceInternal.findById(prefix, id);
        var user = userServiceInternal.findCurrentUser();

        comment.setAuthor(user);
        comment.setProblem(problem);
        comment.setCreated(LocalDateTime.now());

        commentServiceInternal.saveComment(comment);

        return problem.getComments();
    }
}
