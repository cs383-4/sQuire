import models.User;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;

public class ORMTest {
    public static void main(String[] args) {
        User u = new User();
        u.setUsername("HELLO");
        u.save();
        System.out.println(u.getUsername());
    }
}
