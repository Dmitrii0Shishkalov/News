package com.example.news.controller;

import com.example.news.DTO.request.Comments.CommentUpdateRequest;
import com.example.news.DTO.request.Comments.CommentsRequest;
import com.example.news.DTO.response.comments.CommentResponse;
import com.example.news.service.CommentsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentsController {
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/comments")
    public List<CommentResponse> findAllComments() {
        return commentsService.findAllComments();
    }

    @PostMapping("/news/{newsId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable @Min(1) Long newsId,
            @Valid @RequestBody CommentsRequest request) {
        return ResponseEntity.ok(commentsService.createComment(request, newsId));
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody CommentUpdateRequest request) {
        return ResponseEntity.ok(commentsService.updateComment(id, request));
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable @Min(1) Long id) {
        commentsService.deleteComment(id);
    }
}
