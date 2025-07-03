package NewsApp.controller;


import NewsApp.DTO.request.Comments.CommentUpdateRequest;
import NewsApp.DTO.request.Comments.CommentsRequest;
import NewsApp.DTO.response.comments.CommentResponse;
import NewsApp.entity.User;
import NewsApp.security.CustomUserDetails;
import NewsApp.service.CommentsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentsController {
    private static final Logger log = LoggerFactory.getLogger(CommentsController.class);
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
            @Valid @RequestBody CommentsRequest request,
            Authentication authentication) {

        log.info("Creating comment for news {} by user {}", newsId, authentication.getName());

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User author = userDetails.getUser();

        log.info("Author details: {}", author.getId());

        return ResponseEntity.ok(commentsService.createComment(request, newsId, author));
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
