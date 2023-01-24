package drivewayparking.app.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Hidden
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest {

    private Long id;
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
}
