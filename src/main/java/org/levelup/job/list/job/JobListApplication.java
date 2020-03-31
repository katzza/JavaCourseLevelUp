package org.levelup.job.list.job;

import org.levelup.job.list.Domain.Position;
import org.levelup.job.list.Domain.User;
import org.levelup.job.list.Jdbc.JobListService;
import org.levelup.job.list.Jdbc.PositionJdbcService;
import org.levelup.job.list.Jdbc.UserJdbcService;

import java.sql.SQLException;
import java.util.Collection;

public class JobListApplication {
    public static void main(String[] args) throws SQLException {
        JobListService service = new JobListService();
       for (int i =0; i<3; i++)
        {service.createPosition("Scrummaster"+i+i);}
        System.out.println();
        Collection<Position> allPositions = service.findAll();

        for (Position position:allPositions){
            System.out.println(position.getName()+" "+position.getId());
        }

        Collection <Position> likePos = service.findPositionWithNameLike("Dev%");
        for (Position position:likePos){
            System.out.println(position.getName()+" "+position.getId());
        }

       PositionJdbcService pos1 = new PositionJdbcService();
        Position Director = pos1.createPosition("Hr2");
        Collection<Position> likePos1 = pos1.findAllPositionWhichNameLike("Dev%");
        Position x = pos1.findPositionById(1);
        pos1.deletePositionById(2);

        UserJdbcService us = new UserJdbcService();
        us.createUser("Oleg", "Smirnov", "4567 654321");
         User u1 = us.findByPassport("4016 666666");
        Collection<User> u2 = us.findByLastName("Petrov");

    }
}
