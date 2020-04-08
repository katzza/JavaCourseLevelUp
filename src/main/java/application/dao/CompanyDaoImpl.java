package application.dao;

import application.domain.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor  //из всех полей
//Required
//NoArgs
public class CompanyDaoImpl implements CompanyDao {
    private final SessionFactory factory;

    @Override
    public void create(String name, String ein, String address) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        CompanyEntity entity = new CompanyEntity();
        entity.setName(name);
        entity.setAddress(address);
        entity.setEin(ein);
        tx.commit();
        session.close();
    }

    @Override
    public void findById(Integer id) {
        Session session = factory.openSession();
        CompanyEntity loadEntity = session.get(CompanyEntity.class, id);
        System.out.println(loadEntity.getClass().getName());

        session.close();
        System.out.println(loadEntity.getName());
    }

    @Override
    public CompanyEntity findByEin(String ein) {
        Session session = factory.openSession();
        List<CompanyEntity> entities = session.createQuery("from CompanyEntity where ein = :ein", CompanyEntity.class)
                .setParameter("ein", ein)
                .getResultList();
        session.close();
        return entities.isEmpty() ? null : entities.get(0);
    }

    public void findByName(String name) {

    }

    @SneakyThrows
    private void performWithoutTransaction(DatabaseOperation operation) {
        Session session = factory.openSession();
        //action, method
        operation.doAction();
        session.close();
    }

    interface DatabaseOperation {
        void doAction();
    }



    private <TYPE> TYPE perform (Function<Session, TYPE> function){
        Session session = factory.openSession();
        TYPE result = function.apply(session);
        session.close();
        return result;
    }

    CompanyEntity entity =perform (s->s.get(CompanyEntity.class, 1));
}

