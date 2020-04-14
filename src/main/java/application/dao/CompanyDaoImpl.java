package application.dao;

import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import application.domain.CompanyEntity;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;


public class CompanyDaoImpl extends AbstractDao implements CompanyDao {

   public CompanyDaoImpl (SessionFactory factory) {
       super(factory);
   }

  /*  @Override
    public void create(String name, String ein, String address) {
//        Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//
//        CompanyEntity entity = new CompanyEntity();
//        entity.setName(name);
//        entity.setEin(ein);
//        entity.setAddress(address);
//
//        session.persist(entity);
//
//        tx.commit();
//        session.close();

        perform(session -> {
            Transaction tx = session.beginTransaction();

            CompanyEntity entity = new CompanyEntity();
            entity.setName(name);
            entity.setEin(ein);
            entity.setAddress(address);

            session.persist(entity);

            tx.commit();
            return entity;
        });


    }*/

    public void create(String name, String ein, String address){
    runInTransaction (session -> {

        CompanyEntity entity = new CompanyEntity();
        entity.setName(name);
        entity.setEin(ein);
        entity.setAddress(address);
        session.persist(entity);

    });}


    @Override
    public CompanyEntity findById(Integer id) {
//        Session session = factory.openSession();
//        // session.get();
//        //      select * from companies where id = :id
//        //      CompanyEntity(id, name, ein, address)
//
//        // session.load();
//        //      CompanyEntity(id)
//        //      .getName()
//        //          select * from companies where id = :id
//        CompanyEntity loadedEntity = session.get(CompanyEntity.class, id);
//        // if (loadedEntity != null)
//        System.out.println("Entity class: " + loadedEntity.getClass().getName());
//
//        session.close();
//        System.out.println("Company name: " + loadedEntity.getName());

//        performWithoutTransaction(new DatabaseOperation() {
//            @Override
//            public Object doAction(Session session) {
//                Integer id = 3;
//                return session.get(CompanyEntity.class, id);
//            }
//        });

        // (parameters) -> { code here}

        // 0 parameter: ()
        // 1 parameter: session
        // >2 parameters: (param1, param2)

        // session -> session.get(CompanyEntity.class, id)
        // session -> {
        //      CompanyEntity e = session.get(CompanyEntity.class, id);
        //      sout(e.getName());
        // }

        // CompanyEntity doAction(Session session)
        // s -> s.get(CompanyEntity.class, id)
        // s -> {
        //      CompanyEntity e = s.get(CompanyEntity.class, id);
        //      sout(e.getName());
        //      return e;
        // }

        // CompanyEntity entity = performWithoutTransaction(s -> s.get(CompanyEntity.class, id));
//        CompanyEntity entity2 = perform(s -> s
//                .createQuery("from CompanyEntity where ein = :ein", CompanyEntity.class)
//                .setParameter("ein", "")
//                .getSingleResult());)
        CompanyEntity entity = runWithoutTransaction(s -> s.get(CompanyEntity.class, id));
        // class Noname implements DatabaseOperation { @Override doAction(Session s) { s.get(CompanyEntity.class, id) } }
return entity;
    }

    @Override
    public CompanyEntity findByEin(String ein) {


        // HQL - Hibernate Query Language

        // ein = 535-3453
        // select * from companies where ein = :ein
        // List<CompanyEntity> entities = sesion
        return runWithoutTransaction(session -> {
              return   session.createQuery("from CompanyEntity where ein = :ein", CompanyEntity.class)
                .setParameter("ein", ein)
                .getSingleResult();
        });
         // return entities.isEmpty() ? null : entities.iterator().next();
        // return entities.isEmpty() ? null : entities.get(0);
    }

    @SneakyThrows
    public CompanyEntity findByName(String name) {
     //   Method method = getClass().getDeclaredMethod("findByNameInternal", Session.class, String.class);
     //   performDatabaseOperation(method, name);

        List<CompanyEntity> entities  =runWithoutTransaction(session -> {
            return session.createQuery("from CompanyEntity where name = :name", CompanyEntity.class)
                .setParameter("name", name)
                    .getResultList();
        });
        return entities.isEmpty()? null: entities.get(0);
    }

    // a(int, int) -> getDeclaredMethod("a", int.class, int.class)
    // a(int) -> getDeclaredMethod("a", int.class)

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

    private <TYPE> TYPE perform(Function<Session, TYPE> function) {
        Session session = factory.openSession();
        TYPE result = function.apply(session);
        session.close();
        return result;
    }
/*
    private <TYPE> TYPE runInTransaction(Function<Session, TYPE> function) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        TYPE result = function.apply(session);

        session.close();
        transaction.commit();
        return result;
    }*/

    private <T> T performWithoutTransaction(DatabaseOperation<T> operation) {
        Session session = factory.openSession();
        // action, method
        T result = operation.doAction(session);
        session.close();
        return result;
    }

    // class FindByIdOperation implements DatabaseOperation {}

    // FindByIdOperation operation = new FindByOperation();
    // DatabaseOperation operation2 = new FindByOperation();

    // performWithoutTransaction(operation)
    // performWithoutTransaction(operation2)

    @FunctionalInterface
    interface DatabaseOperation<T> {
        T doAction(Session session);
    }

//    class Impl implements DatabaseOperation<CompanyEntity> {
//        @Override
//        public CompanyEntity doAction(Session session) {
//            return null;
//        }
//    }

//    @RequiredArgsConstructor
//    class FindByIdOperation implements DatabaseOperation {
//        private final Integer id;
//        @Override
//        public <T > doAction(Session session) {
//            session.get(CompanyEntity.class, id);
//        }
//    }

}