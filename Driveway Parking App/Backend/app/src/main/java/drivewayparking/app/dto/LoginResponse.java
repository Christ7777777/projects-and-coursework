package drivewayparking.app.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Integer responseID;
    private Boolean isAdmin;
}
