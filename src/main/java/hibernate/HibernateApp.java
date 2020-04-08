package hibernate;

import hibernate.domain.Position;
import hibernate.service.PositionService;
import hibernate.service.UserService;
import org.hibernate.SessionFactory;

import java.util.List;

public class HibernateApp {
    public static void main(String[] args) {
        SessionFactory factory = new JobSessionFactoryConfiguration().configure();
        //  UserService userService = new UserService(factory);
        PositionService positionService = new PositionService(factory);
        /*User user = userService.createUserPersist("Oleg", "Olegoff", "2222 333333");
        System.out.println(user);*/
        // Integer id = userService.createUserSave("ff", "dewded", "1234 555555");
        // User user = userService.createUserPersist("Oleg", "Olegoff", "2222 333333");
        //System.out.println(id);
//Integer cloneId = userService.cloneUser(16, "5555 666755");
        //  System.out.println(cloneId);
        //   User user = userService.updateUserNameWithMerge(3, "Kolian");
        //  System.out.println("copied User"+Integer.toHexString(user.hashCode()));
        //  User user = userService.mergeNewUser("dfs", "dsffs", "1111 1000");
        //System.out.println(user);

        //userService.updateUser("fdf", "fddf", "sdsdds");
        //    positionService.createPositionPersist("Manager");

        Position pos = positionService.getPositionById(1);
        List<Position> pos1 = positionService.getAllPositions();
        System.out.println(pos1.toArray().length);

        List<Position> pos2 = positionService.getPositionByName("Developer");
        System.out.println(pos2.toArray().length);

        List<Position> pos3 = positionService.getPositionByNameLike("Developer");
        System.out.println(pos3.toArray().length);


        positionService.deletePositionById(11);
        //         positionService.deletePositionByName("Hr1");
        factory.close();
    }
}
