package squire;

import squire.Users.Project;
import squire.Users.Session;
import squire.Users.User;

import java.util.Random;
import java.util.UUID;

public class ORMTest
{
    public static void main(String[] args)
    {
        //create a new user with the name hello
        String username = "user" + new Random().nextInt();
        User u = new User(username, "email@email.com", "password123");

        //we need to save it to write the changes to the database
        u.save();
        System.out.println(u.getUsername());

        //get the first user with the username that starts with user
        System.out.println(User.find.where().username.startsWith("user").findList().get(0));

        //Check username/password
        User login_test = User.find.authenticate(username, "password123");
        if (login_test == null)
        {
            //bad username/password combination
            System.out.println("Bad username or password");
        }
        else
        {
            //authentication succeed, log the user in
            Session s = Session.login(login_test);

            //log the user out
            //s.logout();

            //send this token to the client
            String authentication_token = s.getToken();

            //on the server, check to see if the token is active to see if the user is logged in
            Session userSession = Session.find.activeSession(authentication_token);
            if (userSession != null)
            {
                //preform action that requires authentication
                //we can get the user from the session:
                System.out.println(userSession.getUser().getUsername() + " is logged in!");
            }
            else
            {
                //user is not logged in, send user to login page
            }
        }
    }
}
