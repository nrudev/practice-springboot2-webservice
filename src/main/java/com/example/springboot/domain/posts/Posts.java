package com.example.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // 이 클래스가 테이블과 링크될 클래스임을 나타냄. 클래스명(카멜케이스) -> 테이블명(언더스코어). Entity 클래스에서는 절대 Setter 메서드를 만들지 않음.
public class Posts {

    @Id // 이 테이블의 PK값
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY를 추가해야만 auto_increment가 됨.
    private Long id;

    @Column(length = 500, nullable = false) // 기본값 외에 추가로 변경이 필요한 옵션이 있다면 추가하는 어노테이션. 선언하지 않더라도 이 클래스의 필드는 모두 칼럼이 됨.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 빌더를 사용하면 어느 필드에 어떤 값ㅇ르 채워야 할지 명확히 알 수 있음.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
