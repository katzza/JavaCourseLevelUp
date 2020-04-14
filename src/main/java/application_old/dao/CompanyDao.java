package application_old.dao;

import application_old.domain.CompanyEntity;

public interface CompanyDao {

    void create (String name, String ein, String address);
    void findById(Integer id);
    CompanyEntity findByEin(String ein);
    void findByName(String name);
}
