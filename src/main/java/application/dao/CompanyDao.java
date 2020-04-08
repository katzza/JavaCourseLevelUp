package application.dao;

import application.domain.CompanyEntity;
import org.hibernate.type.StringRepresentableType;

public interface CompanyDao {

    void create (String name, String ein, String address);
    void findById(Integer id);
    CompanyEntity findByEin(String ein);

}
