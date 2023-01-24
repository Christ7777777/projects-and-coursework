package drivewayparking.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "disputes")
public class Dispute implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    private String message;
    private Boolean resolved = false;

    @CreationTimestamp
    private Timestamp created;
}
