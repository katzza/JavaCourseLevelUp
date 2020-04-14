package application;


import application.dao.*;
import application.domain.CompanyEntity;
import application.domain.CompanyLegalDetailsEntity;
import application.domain.UserAddressEntity;
import hibernate.domain.User;
import org.hibernate.SessionFactory;
import hibernate.JobSessionFactoryConfiguration;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class JobApplication {

    public static void main(String[] args) {

        SessionFactory factory = new JobSessionFactoryConfiguration().configure();
        CompanyDao companyDao = new CompanyDaoImpl(factory);

     //   CompanyLegalDetailsDao legalDetailsDao = new CompanyLegalDetailsDaoImpl(factory);
   //     companyDao.create("aaaaa", "33333", "444444");
    //    CompanyEntity company = companyDao.findByEin("111");
   //     legalDetailsDao.updateLegalDetailsInCompany(company.getId(), "Sber", "222333");
    //    Collection<CompanyLegalDetailsEntity> legalDetails = legalDetailsDao.findAllbyBankName("Sber");
    //    for (CompanyLegalDetailsEntity a : legalDetails
     //   ) {
    //        System.out.println(a.getCompany().getName());
    //    }

        UserDao userDao  =new UserdaoImpl(factory);
       User user1 = userDao.createUser("dsdfds", "sfdsf", "dfdfff", new ArrayList<>(Arrays.asList(
                "sssssss",
                "aaaaaaa",
                "222222"
        )));
       for (UserAddressEntity addressEntity:user1.getAddresses()){
           System.out.println(addressEntity.getAddress());
       }
        factory.close();

    }

}