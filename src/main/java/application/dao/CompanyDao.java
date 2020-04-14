package application.dao;
// DAO - Data Access Object
// DTO - Data Transfer Object

// Create company form
//   - object - приходит с формы (name, ein, address)
//

// Controller (Resource) DTO
// Service DTO
// DAO (Repository) Entity
// DB

import application.domain.CompanyEntity;

public interface CompanyDao {

    // CRUD - CreateReadUpdateDelete
    // create, findAll, findById, update, delete

    void create(String name, String ein, String address);

    CompanyEntity findById(Integer id);

    CompanyEntity findByEin(String ein);

    CompanyEntity findByName(String name);

}