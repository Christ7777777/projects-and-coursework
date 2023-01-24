package drivewayparking.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor
public class MessageHistory {

    private String message;
    private String name;
    private long otherId;
    private Timestamp sentTime;
    private boolean isSent = false;

}
