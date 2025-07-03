package NewsApp.builder.dto;


import NewsApp.DTO.response.news.NewsAuthorDto;

public class NewsAuthorBuilder {
    private Long id;
    private String username;
    private String email;

    public NewsAuthorBuilder() {}

    public NewsAuthorBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public NewsAuthorBuilder username(String username) {
        this.username = username;
        return this;
    }

    public NewsAuthorBuilder email(String email) {
        this.email = email;
        return this;
    }

    public NewsAuthorDto build() {
        NewsAuthorDto newsAuthorDto  = new NewsAuthorDto();
        newsAuthorDto.setId(id);
        newsAuthorDto.setEmail(email);
        newsAuthorDto.setUsername(username);
        return  newsAuthorDto;

    }
}