package application_old.dao;

import application_old.domain.CompanyEntity;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

//@AllArgsConstructor  //из всех полей
//Required
//NoArgs
@RequiredArgsConstructor
public class CompanyDaoImpl implements CompanyDao {
    private final SessionFactory factory;

    @Override
    public void create(String name, String ein, String address) {
      /*  Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        CompanyEntity entity = new CompanyEntity();
        entity.setName(name);
        entity.setAddress(address);
        entity.setEin(ein);
        tx.commit();
        session.close();*/
        perform(session -> {    //perform также закрывает сессию на строчке
            Transaction tx = session.beginTransaction();

            CompanyEntity entity = new CompanyEntity();
            entity.setName(name);
            entity.setEin(ein);
            entity.setAddress(address);

            session.persist(entity);

            tx.commit();
            return entity;
        });





    }

    @Override
    public void findById(Integer id) {/*    //старый способ без лямбда
        Session session = factory.openSession();
        CompanyEntity loadEntity = session.get(CompanyEntity.class, id);
        System.out.println(loadEntity.getClass().getName());

        session.close();
        System.out.println(loadEntity.getName());*/

        CompanyEntity entity = perform(s -> s.get(CompanyEntity.class, id));
    }

    @Override
    public CompanyEntity findByEin(String ein) {   //старый способ без лямбда
        Session session = factory.openSession();
        List<CompanyEntity> entities = session.createQuery("from CompanyEntity where ein = :ein", CompanyEntity.class)
                .setParameter("ein", ein)
                .getResultList();
        session.close();
        return entities.isEmpty() ? null : entities.get(0);
    }

    @SneakyThrows
    public void findByName(String name) {    //через рефлекшны
        Method method = getClass().getDeclaredMethod("findByNameInternal", Session.class, String.class);
        performDatabaseOperation(method, name);
    }

    private void findByNameInternal(Session session, String name) {
        CompanyEntity entity = session
                .createQuery("from CompanyEntity where name = :name", CompanyEntity.class)
                .setParameter("name", name)
                .getSingleResult();
        System.out.println(entity.getName());
    }

    @SneakyThrows
    private void performDatabaseOperation(Method method, Object... args) {
        // args[name] - Array, 1 element, args[0] -> name

        Session session = factory.openSession();

        // array[ session, args[] ]
        // Session, Object[]
        // Session, String

        Object[] newArgs = new Object[args.length + 1]; // [ , ]
        newArgs[0] = session;  // [ session, ]
        if (args.length > 0) {
            System.arraycopy(args, 0, newArgs, 1, args.length); // [session, name]
        }

        // method.invoke(this, session, args); -> array[ session, args[] ]
        // findByNameInternal(Session s, Object[] array)
        method.invoke(this, newArgs); // -> array[session, string]
        // findByNameInternal(Session s, String string)
        session.close();
    }

    // Supplier: () -> return id * 6;
    // Consumer: (arg) -> sout(arg) -> collection.forEach(el -> sout(el);
    // Function: collection.stream().map(string -> string.length());


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

