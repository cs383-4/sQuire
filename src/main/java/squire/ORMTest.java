package squire;

import squire.models.User;
import squire.models.query.QUser;

public class ORMTest {
    public static void main(String[] args) {
        User u = new User();
        u.setUsername("HELLO");
        u.save();
        System.out.println(u.getUsername());
        System.out.println(new QUser().username.equalTo("HELLO").findList().size());
    }
}
