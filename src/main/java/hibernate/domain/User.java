package hibernate.domain;

import application.domain.UserAddressEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")  //name of table
public class User {     //name of entity
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //serializable
    private Integer id;
    @Column(length = 100, nullable = false) //при создании и для валидации при запуске
    private String name;
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;
    @Column(length = 50, nullable = false, unique = true)
    private String passport;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="user_id")
    private List<UserAddressEntity> addresses;
}
