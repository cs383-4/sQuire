package squire.Users;

/**
 * Implements a user model containing the username and password, as well as any other information associated with a user
 * <p>
 * The usage is as such:
 * Create a new user:
 * User u = new User(username, password;
 * <p>
 * Authenticate a user:
 * User u = User.find.authenticate(username, password)
 * <p>
 * The user can now be logged in with
 * Session.login(u)
 */

import javax.persistence.*;

import squire.BaseModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

@Entity
//some databases have user as a reserved word, so following ebean examples, prefix tables with "o_"
@Table(name = "o_user")
public class User extends BaseModel {
    public static final UserFinder find = new UserFinder();

    @Column(nullable = false, unique = true) //field cannot be empty, and must be unique
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany
    private ArrayList<Project> projects;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<Project> workingOn;

    private void setEmail(String val) {
        email = val;
    }

    private String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addProject(Project p) {
        projects.add(p);
    }

    public List<Project> getWorkingOn() {
        return workingOn;
    }

    public void setWorkingOn(List<Project> workingOn) {
        this.workingOn = workingOn;
    }

    /**
     * Sets the given password for the user. Hashes it, and stores it in the database
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        try {
            this.password = PasswordHash.createHash(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            /* this will only happen if the correct crypto libraries aren't installed,
               no reason to keep going then, so quit the program
             */
            ex.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Verifies the given password and returns a true value if it matches the one in the database.
     *
     * @param password the password to check
     * @return a boolean value, true if the password matches the stored one, else false
     */
    public boolean checkPassword(String password) {
        try {
            return PasswordHash.validatePassword(password, this.password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
        //a return statement to keep intellij happy
        return false;
    }

    public User(String username, String email, String password) {
        super();
        projects = new ArrayList<Project>();
        setUsername(username);
        setEmail(email);
        setPassword(password);
        this.save();
    }

}
