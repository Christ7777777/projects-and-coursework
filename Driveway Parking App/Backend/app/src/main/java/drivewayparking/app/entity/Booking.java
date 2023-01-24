package drivewayparking.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "bookings")
public class Booking implements Serializable, Comparable<Booking> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "uid")
    private User user;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pid")
    private Property property;

    @NotNull
    @Column
    private Timestamp check_in;

    @NotNull
    @Column
    private Timestamp check_out;

    @NotNull
    @Column
    private double cost;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    private Rating rating;

    @CreationTimestamp
    private Timestamp created;

    @Override
    public int compareTo(Booking other) {
        return this.check_out.compareTo(other.getCheck_out());
    }

}
