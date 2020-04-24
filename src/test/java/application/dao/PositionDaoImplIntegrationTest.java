package application.dao;

import application.domain.PositionEntity;
import configuration.HibernateTestConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;

public class PositionDaoImplIntegrationTest {
    private static SessionFactory factory;
    private static PositionDao positionDao;

    @BeforeAll
    public static void setupPositionDao() {
        factory = HibernateTestConfiguration.getFactory();
        positionDao = new PositionDaoImpl(HibernateTestConfiguration.getFactory());
    }


     @Test
    @DisplayName("")
   public void testCreatePosition_when_PositionWithNameNotExist_thenCreateNewPosition() {
        String name = "Java Developer1234567";
        PositionEntity result = positionDao.createPosition(name);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(name, result.getName());

        Session session = factory.openSession();
        PositionEntity fromDb = session.createQuery("from PositionEntity where name = :name", PositionEntity.class)
                .setParameter("name", name)
                .getSingleResult();
        Assertions.assertNotNull(fromDb);
        session.close();
    }

    @Test
    @DisplayName("Throw exception when position with this name exist")
    public void testCreatePosition_when_PositionWithNameExist_thenThrowException() {
        String name = "PositionName";
        positionDao.createPosition(name);
        Assertions.assertThrows(PersistenceException.class, () -> positionDao.createPosition(name));

    }
}