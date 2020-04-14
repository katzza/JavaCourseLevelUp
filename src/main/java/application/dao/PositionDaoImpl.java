package application.dao;

import application.domain.PositionEntity;
import org.hibernate.SessionFactory;

public class PositionDaoImpl extends AbstractDao implements PositionDao {
    public PositionDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public PositionEntity createPosition(String name) {
       return  runInTransaction( session -> {
           PositionEntity position = new PositionEntity();
           position.setName(name);
           session.persist(position);
           return position;

       });
    }
}
