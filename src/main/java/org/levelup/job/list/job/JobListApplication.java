package org.levelup.job.list.job;
import org.levelup.job.list.Domain.Position;
import  org.levelup.job.list.Jdbc.JobListService;
import org.levelup.job.list.Jdbc.PositionJdbcService;

import java.sql.SQLException;
import java.util.Collection;

public class JobListApplication {
    public static void main(String[] args) throws SQLException {
        JobListService service = new   JobListService();
       /* for (int i =0; i<10; i++)
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
        Position Director = pos1.createPosition("Hr");
        Position Director1 = pos1.createPosition("Director");
        Position Director2 = pos1.createPosition("Director");
    }
}
