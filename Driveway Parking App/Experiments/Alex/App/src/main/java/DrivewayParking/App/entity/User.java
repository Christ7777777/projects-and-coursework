package DrivewayParking.App.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String email;
    private String password;
    private String phoneNumber;
    private String name;

    // private int role;

}
