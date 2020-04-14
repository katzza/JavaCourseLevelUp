package hibernate.service;

import application.domain.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class UserService {
    private final SessionFactory factory;

    public UserService(SessionFactory factory) {
        this.factory = factory;
    }

    public UserEntity createUserPersist(String name, String lastName, String passport) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        UserEntity user = new UserEntity();  //transient
        user.setName(name);
        user.setLastName(lastName);
        user.setPassport(passport);

        session.persist(user);   //manager /persistent

        transaction.commit();

        session.close(); //user -detached
        return user;
    }

    public Integer createUserSave(String name, String lastName, String passport) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        UserEntity user = new UserEntity();  //transient
        user.setName(name);
        user.setLastName(lastName);
        user.setPassport(passport);
        Integer generatedId = (Integer) session.save(user);
//= user.getId    остатки первоначального hibernate

        transaction.commit();
        session.close(); //user -detached
        return generatedId;
    }

    public UserEntity getById(Integer id) {
        Session session = factory.openSession();
        UserEntity user = session.get(UserEntity.class, id);
        session.close();
        return user;
    }

    public Integer cloneUser(Integer id, String passport) {
        UserEntity user = getById(id); //datached
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        user.setPassport(passport); //datached
        Integer cloneId = (Integer) session.save(user);//managed
        transaction.commit();
        session.close();
        return cloneId;
    }

    public UserEntity updateUserNameWithMerge(Integer id, String name) {
        UserEntity user = getById(id);

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        user.setName(name);
        UserEntity mergedUser = (UserEntity) session.merge(user);

        transaction.commit();
        session.close();
        System.out.println("original User" + Integer.toHexString(user.hashCode()));

        return mergedUser;
    }

    public UserEntity mergeNewUser(String name, String lastName, String passport) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        UserEntity user = new UserEntity();
        user.setName(name);
        user.setLastName(lastName);
        user.setPassport(passport);

        UserEntity newUser = (UserEntity) session.merge(user);
        transaction.commit();
        session.close();
        return newUser;
    }


    public void updateUser(String name, String lastName, String passport) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        UserEntity user = new UserEntity();
        user.setName(name);
        user.setLastName(lastName);
        user.setPassport(passport);

        session.update(user);

        transaction.commit();
        session.close();
    }
}