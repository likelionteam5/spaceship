package study.security.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageDto {
    @NotNull
    @Column(length = 5)
    private String name;

    @NotNull
    @Column(length = 20)
    private String birth;

    @NotNull
    @Column(length = 5)
    private String gender;
}
