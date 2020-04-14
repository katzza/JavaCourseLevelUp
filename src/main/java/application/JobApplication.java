package application;


import application.dao.*;
import application.domain.CompanyEntity;
import application.domain.JobListEntity;
import application.domain.UserAddressEntity;
import application.domain.UserEntity;
import org.hibernate.SessionFactory;
import hibernate.JobSessionFactoryConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class JobApplication {

    public static void main(String[] args) {

        SessionFactory factory = new JobSessionFactoryConfiguration().configure();
        CompanyDao companyDao = new CompanyDaoImpl(factory);

     //   CompanyLegalDetailsDao legalDetailsDao = new CompanyLegalDetailsDaoImpl(factory);
        companyDao.create("tr", "tr", "tr");
       Integer companyId = companyDao.findByEin("tr").getId();
   //     legalDetailsDao.updateLegalDetailsInCompany(company.getId(), "Sber", "222333");
    //    Collection<CompanyLegalDetailsEntity> legalDetails = legalDetailsDao.findAllbyBankName("Sber");
    //    for (CompanyLegalDetailsEntity a : legalDetails
     //   ) {
    //        System.out.println(a.getCompany().getName());
    //    }

        UserDao userDao  =new UserdaoImpl(factory);
       UserEntity user1 = userDao.createUser("trr", "trr", "trr", new ArrayList<>(Arrays.asList(
                "sssssss",
                "aaaaaaa",
                "222222"
        )));
       for (UserAddressEntity addressEntity:user1.getAddresses()){
           System.out.println(addressEntity.getAddress());
       }
       PositionDao positionDao = new PositionDaoImpl(factory);
       Integer positionId = positionDao.createPosition("trr").getId();

       JobListDao jobListDao = new JobListDaoImpl(factory);
  //   JobListEntity jobRecord =   jobListDao.createJobRecord(companyId, user1.getId(), positionId, LocalDate.of(2019, 12, 4),
   //             null);

JobListEntity jobRecord = jobListDao.findJobRecord(companyId, user1.getId(), positionId);
        factory.close();

    }

}