package drivewayparking.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating implements Serializable {

    @Id
    private Long id;

    @NotNull
    private Float accommodation;

    @NotNull
    private Float safety;

    @NotNull
    private Float responsiveness;

    private String comments;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private Booking booking;

    @CreationTimestamp
    private Timestamp created;
    @Override
    public String toString() {
        return "rating id: " + this.id + "\n" +
                "accommodation: " + this.accommodation + "\n" +
                "safety: " + this.safety + "\n" +
                "responsiveness: " + this.responsiveness + "\n" +
                "comments: " + this.comments;
    }

}
