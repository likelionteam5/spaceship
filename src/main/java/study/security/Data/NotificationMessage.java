package study.security.Data;

import lombok.Data;

@Data
public class NotificationMessage {

    private String recipientToken;
    private String title;
    private String body;
}
