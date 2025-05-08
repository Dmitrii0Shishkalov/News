package com.example.news.DTO.request.Comments;

//import com.example.news.DTO.response.comments.CommentNewsResponse;
//import com.example.news.DTO.response.comments.CommentUserResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
public class CommentsRequest {
    @NotBlank(message = "Содержание комментария не может быть пустым")
    @Size(max = 2000, message = "Комментарий не должен превышать 2000 символов")
    private String content;

    @NotNull(message = "User ID cannot be null")  // Для Long используем @NotNull
    private Long userId;

    public String getContent() {
            return content;
        }



    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }
}