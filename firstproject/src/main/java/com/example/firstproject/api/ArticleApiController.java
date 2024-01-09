package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j // 로그 찍을 수 있게 어노테이션 추가
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;
    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity();
        log.info("id : {}, article: {}", id, article.toString());

        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()){
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id : {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // ResponseEntity 반환
            // Article을 ResponseEntity에 담아서 반환해야만 반환하는 데이터에 상태 코드를 실어 보낼 수 있다.
        }

        // 4. 업데이트 및 정상 응답(200)하기
        // target 에는 기존 데이터 , article에는 수정할 데이터
        target.patch(article); // 기존 데이터에 새 데이터 붙이기
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated); // 정상 응답

        /*
        Article updated = articleRepository.save(article); // article 엔티티 DB에 저장
        return ResponseEntity.status(HttpStatus.OK).body(updated); // 정상 응답
        */
    }
    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // 1. DB에 대상 엔티티 있는지 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리하기
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 3. 대상 삭제, 정상 응답(200) 반환
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build(); // build() 메서드로 생성된 객체 = body(null)
    }
}
