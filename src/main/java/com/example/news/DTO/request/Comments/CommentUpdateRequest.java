package com.example.news.DTO.request.Comments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentUpdateRequest {
    @NotBlank(message = "Содержание комментария не может быть пустым")
    @Size(max = 2000, message = "Комментарий не должен превышать 2000 символов")
    private String content;

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
