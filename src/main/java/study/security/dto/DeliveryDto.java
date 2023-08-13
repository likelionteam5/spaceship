package study.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.security.Entity.Delivery;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {

    private Long id;
    private String location;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;

    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    public Delivery toDeliveryEntity(Delivery delivery) {
        delivery.setBoardWriter(this.getBoardWriter());
        delivery.setBoardTitle(this.getBoardTitle());
        delivery.setLocation(this.getLocation());
        delivery.setBoardContents(this.getBoardContents());
        return delivery;
    }
}

