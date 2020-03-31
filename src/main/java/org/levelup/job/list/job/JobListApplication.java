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
       /*for (int i =0; i<10; i++)
        {service.createPosition("Developer"+i+i);}
        System.out.println();
        Collection<Position> allPositions = service.findAll();

        for (Position position:allPositions){
            System.out.println(position.getName()+" "+position.getId());
        }

        Collection <Position> likePos = service.findPositionWithNameLike("Dev%");
        for (Position position:likePos){
            System.out.println(position.getName()+" "+position.getId());
        }*/

       PositionJdbcService pos1 = new PositionJdbcService();
        Position Director = pos1.createPosition("Hr1");
        Collection<Position> likePos = pos1.findAllPositionWhichNameLike("Dev%");
        Position x = pos1.findPositionById(1);
       /*  pos1.deletePositionById(1);
       // pos1.deletePositionById(100);
      //   pos1.deletePositionByName("Sec");
        pos1.deletePositionByName("devops");
        pos1.findAllPositions();
        pos1.findPositionByName("Developer");*/
        UserJdbcService us = new UserJdbcService();
    //    us.createUser("Vasj", "Petrov", "0000 600000");
   //     us.createUser("Losj", "Nessky", "1111 555555");
        User u1 = us.findByPassport("4016 666666");
        Collection<User> u2 = us.findByLastName("Petrov");
    //    us.findByLastName("AAAAA");
        ;
    }
}
