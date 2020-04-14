package application_old.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Setter
@Getter
@Entity
@Table (name = "companys")

public class CompanyEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (nullable = false)
    private String name;
    @Column (nullable = false, unique = true)
    private String ein;
    @Column (nullable = false)
    private String address;


}

