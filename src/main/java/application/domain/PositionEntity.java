package application.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table (name ="positions")
public class PositionEntity {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private Integer id;
    @Column (unique =true)
    private String name;

    @ManyToMany (mappedBy = "positions")
    private List <CompanyEntity> companies;

    @Override
    public String toString() {
        return "PositionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

