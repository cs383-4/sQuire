package squire;

import squire.Users.Session;
import squire.Users.User;
import squire.Users.query.QSession;
import squire.Users.query.QUser;

import java.util.UUID;

public class ORMTest {
    public static void main(String[] args) {
        //create a new user with the name hello
        User u = new User();
        u.setUsername("HELLO");
        u.setPassword("password123");
        //we need to save it to write the changes to the database
        u.save();
        System.out.println(u.getUsername());

        //get the first user with the username equal to hello
        User y = new QUser().username.equalTo("HELLO").findList().get(0);
        //check the password, should print true
        System.out.println(y.checkPassword("password123"));

        Session s = new Session(y);
        String token = s.getToken();

        System.out.println(Session.getActiveSession(token));
    }
}
