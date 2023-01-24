package drivewayparking.app.dto;

import drivewayparking.app.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisputeResponse {
    private Long id;
    private Long bookingId;
    private Long adminId;
    private String message;
    private Boolean resolved;
    private Timestamp created;
}
