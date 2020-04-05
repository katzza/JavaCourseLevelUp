package hibernate;

import hibernate.service.UserService;
import org.hibernate.SessionFactory;

public class HibernateApp {
    public static void main(String[] args) {
        SessionFactory factory = new JobSessionFactoryConfiguration().configure();
        UserService userService = new UserService(factory);
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

        userService.updateUser("fdf", "fddf", "sdsdds");
        factory.close();
    }
}
