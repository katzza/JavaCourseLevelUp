package application.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// Flyway (.sql)
// Liquebase (.xml)

@Setter
@Getter
@Entity
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String ein;
    @Column(nullable = false)
    private String address;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name ="id")
    private CompanyLegalDetailsEntity legalDetails;

}