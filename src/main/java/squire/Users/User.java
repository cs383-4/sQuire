package squire.Users;

import javax.persistence.*;
import com.avaje.ebean.Model;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

@Entity
//some databases have user as a reserved word, so following ebean examples, prefix tables with "o_"
@Table(name = "o_user")
public class User extends Model {
    @Id
    Long id;

    @Version
    Long version;

    @Column(length = 200)
    private String username;

    @Column(length = 200)
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the given password for the user. Hashes it, and stores it in the database
     * @param password the password to set
     */
    public void setPassword(String password) {
        try {
            this.password = PasswordHash.createHash(password);
        } catch(NoSuchAlgorithmException | InvalidKeySpecException ex) {
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
        } catch(NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
        //a return statement to keep intellij happy
        return false;
    }
}
