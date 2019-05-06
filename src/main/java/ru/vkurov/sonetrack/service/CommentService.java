package ru.vkurov.sonetrack.service;

import java.util.List;
import ru.vkurov.sonetrack.data.entity.CommentEntity;

public interface CommentService {
    List<CommentEntity> addCommentAndReturnWithOthers(String prefix, Long id, CommentEntity comment);
}
