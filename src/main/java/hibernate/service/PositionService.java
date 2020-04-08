package hibernate.service;

import hibernate.domain.Position;
import hibernate.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PositionService {
    private final SessionFactory factory;

    public PositionService(SessionFactory factory) {
        this.factory = factory;
    }

    public Position createPositionPersist(String name) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Position position = new Position();  //transient
        position.setName(name);

        session.persist(position);   //manager /persistent
        transaction.commit();
        session.close(); //user -detached
        return position;
    }

    public Position getPositionById(Integer id) {
        Session session = factory.openSession();
        Position position = session.get(Position.class, id);
        session.close();
        return position;
    }

    public List<Position> getAllPositions() {
        Session session = factory.openSession();
        List<Position> positions = session.createQuery("FROM Position").list();
        session.close();
        return positions;
    }

    public List<Position> getPositionByName(String nameParam) {
        Session session = factory.openSession();
        var query = session.createQuery("FROM Position WHERE name = :param");
        query.setParameter("param", nameParam);
        List<Position> positions = query.list();
        session.close();
        return positions;
    }

    public List<Position> getPositionByNameLike(String nameParam) {
        Session session = factory.openSession();
        var query = session.createQuery("FROM Position WHERE name LIKE :param");
        query.setParameter("param", "%" + nameParam + "%");
        List<Position> positions = query.list();
        session.close();
        return positions;
    }

    public void deletePositionById(Integer id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Position position = getPositionById(id);  //transient
        if (position != null) {
            session.delete(position);
            System.out.println("DELETED position" + id);
        }  //manager /persistent*/
        else System.out.println("Delete ERROR:  Id not found");
       /* Query query =  session.createQuery("delete Position where id = :param");
        query.setParameter("param", id);
        query.executeUpdate();*/
        transaction.commit();
        session.close(); //user -detached
    }

    public void deletePositionByName(String name) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery("FROM positions WHERE (UPPER)(?) = :(UPPER)(?)")
                .setParameter("name", name).executeUpdate();
        transaction.commit();
        session.close(); //user -detached
    }

}
