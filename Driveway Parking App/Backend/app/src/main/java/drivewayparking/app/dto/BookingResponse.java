package drivewayparking.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse implements Comparable<BookingResponse> {

    private Long id;
    private Long pid;
    private Long uid;
    private Timestamp check_in;
    private Timestamp check_out;
    private double cost;
    private double hours;

    @Override
    public int compareTo(BookingResponse other) {
        return this.check_out.compareTo(other.getCheck_out());
    }
}
