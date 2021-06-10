package com.example.springboot.web.dto;

import com.example.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

    // 브라우저에 http://localhost:8080/api/v1/posts/1 을 입력해 API 조회 기능을 테스트했을 때,
    // 모두 null 로 뜨는 문제가 있었는데, 위 생성자를 잘못 작성했기 때문에 발생한 문제였음.
}
