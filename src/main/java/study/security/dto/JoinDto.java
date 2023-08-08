package study.security.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class JoinDto {
    @Column(length = 25)
    @NotNull
    private String username;
    @Column(length = 200)
    @NotNull
    private String password;
    @Column(length = 20)
    @NotNull
    private String ck_password;
    @Column(length = 20)
    @NotNull
    private String name;
    @Column(length = 10)
    @NotNull
    private String birth;
    @Column(length = 5)
    @NotNull
    private String gender;
    @Column(length = 20)
    @NotNull
    private String phoneNumber;

}
