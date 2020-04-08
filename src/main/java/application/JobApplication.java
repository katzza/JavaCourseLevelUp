package application;

import application.dao.CompanyDao;
import application.dao.CompanyDaoImpl;
import application.domain.CompanyEntity;
import hibernate.JobSessionFactoryConfiguration;
import org.hibernate.SessionFactory;

public class JobApplication {
    public static void main(String[] args) {
        SessionFactory factory = new JobSessionFactoryConfiguration().configure();
       // CompanyDao companyDao = new CompanyDaoImpl(factory);
      //  companyDao.findById(1);
    //   CompanyEntity entity = companyDao.findByEin("1234580045");
     //   System.out.println(entity);
        factory.close();
    }
}
