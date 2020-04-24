package application.dao;

import application.domain.PositionEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class PositionDaoImplTest {
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;
    private PositionDao dao;

    @BeforeAll
    public void beforAll() {
    }

    @BeforeEach
    public void initialize() {
        factory = mock(SessionFactory.class); //Proxy
        session = mock(Session.class);
        transaction = mock(Transaction.class);
        Mockito.when(factory.openSession()).thenReturn(session);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    public void testCreatePosition_validParams_persistNewPosition() {
        String name = "position name";
        PositionEntity entity = dao.createPosition(name);
        assertEquals(name, entity.getName());
     //   Mockito.verify(transaction).commit();
        verify(session).persist(entity);
        Mockito.verify(transaction, Mockito.times(1)).commit();
        Mockito.verify(session).close();
    }

    @AfterAll
    public void afterAll() {
        System.out.println("After all");
    }
}