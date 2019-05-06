package ru.vkurov.sonetrack.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vkurov.sonetrack.service.facade.CommentFacade;
import ru.vkurov.sonetrack.web.constants.Urls;
import ru.vkurov.sonetrack.web.dto.comment.CommentCreateRequest;
import ru.vkurov.sonetrack.web.dto.comment.CommentDto;

@RestController
@RequiredArgsConstructor
@RequestMapping(Urls.Problem.Comment.FULL)
public class CommentController {
    private final CommentFacade commentFacade;

    @PostMapping
    public List<CommentDto> addComment(@PathVariable("prefix") String prefix,
                                       @PathVariable("id") Long id, @RequestBody CommentCreateRequest request) {
        return commentFacade.addComment(prefix, id, request);
    }
}
