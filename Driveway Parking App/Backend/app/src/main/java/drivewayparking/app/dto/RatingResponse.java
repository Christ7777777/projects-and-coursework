package drivewayparking.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponse {

    private Long rid;
    private Long uid;
    private Long pid;
    private Float accommodation;
    private Float safety;
    private Float responsiveness;
    private String comments;

    @Override
    public String toString() {
        return "rating id: " + this.rid + "\n" +
                "accommodation: " + this.accommodation + "\n" +
                "safety: " + this.safety + "\n" +
                "responsiveness: " + this.responsiveness + "\n" +
                "comments: " + this.comments;
    }

}
