package application.domain;

import hibernate.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_addresses")
@NoArgsConstructor
@Data
public class UserAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String address;

    @ManyToOne
    private User user;
}
