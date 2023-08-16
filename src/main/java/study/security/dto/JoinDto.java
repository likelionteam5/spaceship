package study.security.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class JoinDto {
    @Column(length = 25)
    @NotBlank(message = "아이디를 입력해주세요")
    private String username;
    @Column(length = 200)
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
    @Column(length = 20)
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String ck_password;
    @Column(length = 20)
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @Column(length = 10)
    @NotBlank(message = "생일을 입력해주세요")
    private String birth;
    @Column(length = 5)
    @NotBlank(message = "성별을 입력해주세요")
    private String gender;
    @Column(length = 20)
    @NotBlank(message = "전화번호를 입력해주세요")
    private String phoneNumber;


}
