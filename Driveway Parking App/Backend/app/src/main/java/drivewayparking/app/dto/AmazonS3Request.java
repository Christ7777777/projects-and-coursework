package drivewayparking.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmazonS3Request {
    private Long userId;
    private Long propertyId;
    private Long disputeId;
    private Long adminId;
}
