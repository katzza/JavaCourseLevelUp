package application_old;

import application_old.dao.CompanyDao;
import application_old.dao.CompanyDaoImpl;
import hibernate.JobSessionFactoryConfiguration;
import org.hibernate.SessionFactory;

public class JobApplication1 {
    public static void main(String[] args) {
        SessionFactory factory = new JobSessionFactoryConfiguration().configure();
        CompanyDao companyDao = new CompanyDaoImpl(factory);
        companyDao.findById(1);
    //   CompanyEntity entity = companyDao.findByEin("1234580045");
     //   System.out.println(entity);
        factory.close();
    }
}
