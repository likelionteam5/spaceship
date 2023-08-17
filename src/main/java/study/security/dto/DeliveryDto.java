package study.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.security.Entity.Delivery;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {

    private Long id;
    private String location;
    private String boardTitle;
    private String boardContents;

    private LocalDate boardCreatedTime;
    private LocalDate boardUpdatedTime;

    public Delivery toDeliveryEntity(Delivery delivery) {
        delivery.setBoardCreatedTime(this.getBoardCreatedTime());
        delivery.setBoardUpdatedTime(this.getBoardUpdatedTime());
        delivery.setBoardTitle(this.getBoardTitle());
        delivery.setLocation(this.getLocation());
        delivery.setBoardContents(this.getBoardContents());

        if (this.getBoardCreatedTime() != null) {
            delivery.setBoardCreatedTime(this.getBoardCreatedTime());
        } else {
            delivery.setBoardCreatedTime(LocalDate.now());
        }

        delivery.setBoardUpdatedTime(LocalDate.now());

        return delivery;
    }
}