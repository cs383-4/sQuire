package squire;

import squire.Users.User;
import squire.Users.query.QUser;

public class ORMTest {
    public static void main(String[] args) {
        //create a new user with the name hello
        User u = new User();
        u.setUsername("HELLO");
        //we need to save it to write the changes to the database
        u.save();
        System.out.println(u.getUsername());

        //get the first user with the username equal to hello
        User y = new QUser().username.equalTo("HELLO").findList().get(0);
        //set the password and save
        y.setPassword("password123");
        y.save();
        //check the password, should print true
        System.out.println(y.checkPassword("password123"));
    }
}
