package drivewayparking.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {

    private Long id;
    private Float accommodation;
    private Float safety;
    private Float responsiveness;
    private String comments;

}
