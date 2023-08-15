package study.security.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Builder
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId; //Id

    @Column(length = 25)
    private String username; // 로그인 하는 아이디
    @Column(length = 200)
    private String password;
    @Column(length = 20)
    private String name;
    @Column(length = 10)
    private String birth;
    @Column(length = 5)
    private String gender;
    @Column(length = 20)
    private String phoneNumber;
    @Column(name = "activated")
    private boolean activated;

    // 각 뱃지 유무
    @Column
    private Boolean KioskBadge ;
    @Column
    private Boolean DeliveryBadge;
    @Column
    private Boolean SearchingBadge;
    @Column
    private Boolean TrainBadge;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}


