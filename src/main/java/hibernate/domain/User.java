package hibernate.domain;

import javax.persistence.*;

@Entity
@Table(name = "users")  //name of table
public class User {     //name of entity
    @Id //primary key
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    //serializable
    private Integer id;
    @Column (length = 100, nullable = false) //при создании и для валидации при запуске
    private String name;
  @Column (name = "last_name", length = 100, nullable = false)
    private String lastName;
  @Column (length = 50, nullable = false, unique = true)
    private String passport;

  public User(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }

    public void setPassport(String passport) {
        this.passport = passport;


    }
}
