package study.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.security.Entity.Delivery;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {

    private String boardWriter;
    private String boardTitle;
    private String location;
    private String boardContent;

    public Delivery toDeliveryEntity() {
        Delivery delivery = new Delivery();
        delivery.setBoardWriter(this.getBoardWriter());
        delivery.setBoardTitle(this.getBoardTitle());
        delivery.setLocation(this.getLocation());
        delivery.setBoardContent(this.getBoardContent());
        return delivery;
    }
}

