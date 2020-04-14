package application.dao;

import application.domain.CompanyLegalDetailsEntity;

import java.util.Collection;

public interface CompanyLegalDetailsDao {
    void updateLegalDetailsInCompany (Integer companyId, String bankName, String bic);
    Collection <CompanyLegalDetailsEntity> findAllbyBankName (String bankName);

}
