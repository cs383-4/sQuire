package squire;

import squire.Users.Session;
import squire.Users.User;

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
        User y = User.find.where().username.equalTo("HELLO").findList().get(0);
        //check the password, should print true
        System.out.println(y.checkPassword("password123"));

        //create a session, get the token, and then retrieve the session
        Session s = new Session(y);
        String token = s.getToken();

        System.out.println(Session.find.activeSession(token).getUser().getUsername());
    }
}
