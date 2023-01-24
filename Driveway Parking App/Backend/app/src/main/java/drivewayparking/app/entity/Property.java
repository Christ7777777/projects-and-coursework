package drivewayparking.app.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "properties")
public class Property implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String title;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private Integer zipcode;

    @NotNull
    private String country;

    @NotNull
    @Column(precision = 10, scale = 2)
    private Double latitude;

    @NotNull
    @Column(precision = 10, scale = 2)
    private Double longitude;

    @NotNull
    private String description;

    @NotNull
    private Double ratePerHour;

    // change to false later when developing admin features
    private Boolean approved = true;

    // parking type
    private Boolean driveway = false;
    private Boolean garage = false;
    private Boolean parkingLot = false;
    private Boolean handicapped = false;

    // vehicle type
    private Boolean car = false;
    private Boolean truck = false;
    private Boolean motorcycle = false;
    private Boolean oversized = false;

    // amenities
    private Boolean EVcharging = false;
    private Boolean inOut = false;
    private Boolean tailgating = false;
    private Boolean shuttle = false;

    @CreationTimestamp
    private Timestamp created;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Property other = (Property) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
