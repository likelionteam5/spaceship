package study.security.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 DB에 위임
    private Long id;

    @Column(length = 30, nullable = false)
    private String boardTitle; // 제목

    @Column(length = 15, nullable = false)
    private String location; // 지역

    @Column(length = 500, nullable = false)
    private String boardContents; // 내용

    @Column(length = 20, nullable = false)
    private String boardWriter; // 작성자
}