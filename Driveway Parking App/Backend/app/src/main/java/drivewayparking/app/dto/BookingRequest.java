package drivewayparking.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    private Long uid;
    private Long pid;
    private Timestamp check_in;
    private Timestamp check_out;

}
