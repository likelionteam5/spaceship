package study.security.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "boards")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터 베이스에 자동 키 생성 권한을 위임함.
    @Column(name = "board_id")
    private Long id ;
    @Column(length = 20 , nullable = false)
    private String title;
    @Column(length = 300 , nullable = false)
    private String content;
    @Column(length = 100, nullable = false)
    private String writer; // 일단 String 으로 저장해 놓고 repo에서 UserDTO 정보를 받아와야 하지 않을까 생각.
    @Column(length = 30, nullable = false)
    private String region;


    //메서드에 @Builder 어노테이션을 붙이면 초기화 로직이 생성자 메서드 내부에 직접 명시되어 있어서 초기화 로직의 구체적인 내용을 더 명확하게 볼 수 있습니다.
    @Builder
    public Board(String writer, String title, String content, String region) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.region = region;
    }
}
