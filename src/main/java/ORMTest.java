import models.User;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;

public class ORMTest {
    private static EbeanServer server = Ebean.getDefaultServer();

    public void main() {
        User u = new User();
        u.setUsername("HELLO");
        u.save();
    }
}
