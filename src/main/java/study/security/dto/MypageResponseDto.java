package study.security.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MypageResponseDto {
    boolean kioskBadge;
    boolean deliveryBadge;
    boolean trainBadge;
    String name;
    String gender;
    String birth;
}


