package application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table (name ="positions")
public class PositionEntity {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany (mappedBy = "positions")
    private List <CompanyEntity> companies;
}

