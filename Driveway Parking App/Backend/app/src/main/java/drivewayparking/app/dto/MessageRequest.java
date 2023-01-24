package drivewayparking.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {

    private String message;
    private long sender_id;
    private long receiver_id;
    private Timestamp sentTime;
    private boolean isSent;


}
