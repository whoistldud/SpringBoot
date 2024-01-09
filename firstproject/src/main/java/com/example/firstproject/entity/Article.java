package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 추가 어노테이션
@ToString
@Entity
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동 생성(겹치지 않게)
    private  Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        // 수정할 내용이 있는 경우에만 동작할 메소드
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }

    /* 게터 메소드를 만드는 방법 1
    public Long getId() { // 주의! 데이터 타입을 String -> Long 변경해야 함 !
        return id;
    } */

    // Article 생성자 추가
//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

    // toString() 메서드 추가
//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

}
