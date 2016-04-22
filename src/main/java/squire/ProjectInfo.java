package squire.Users;
/**
 * Implements a project storage model. Project metadata is kept in one table, actual files are kept in another table.
 */

import squire.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "o_project_info")
public class ProjectInfo extends BaseModel {
 //   public static final SessionFinder find = new SessionFinder();
 //   private static final long SESSION_LENGTH = 7 * 24 * 60 * 60 * 1000; //1wk

    @Column(nullable = false)
    @ManyToOne
    private User user;

    @Column(nullable = false)
    private Timestamp expires;
    @Column(nullable = false, unique = true)
    private UUID token;

    public String getToken() {
        return token.toString();
    }

    private void generateToken() {
        this.token = UUID.randomUUID();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Check to see if the session is expired
     *
     * @return true if the session is expired, else false
     */
    public boolean isExpired() {
        Timestamp now = new Timestamp(new Date().getTime());
        return now.after(expires);
    }

    /**
     * Set the session to expire in SESSION_LENGTH from now
     */
 /*   private void updateExpires() {
        this.expires = new Timestamp(new Date().getTime() + SESSION_LENGTH);
    }
*/
    /**
     * Generate an active session for the given user
     * Note: you should check the password with User.find.authenticate first!
     *
     * @param u the user to create the session for
     * @return the new session
     */
    public static ProjectInfo login(User u) {
        ProjectInfo s = new ProjectInfo();
        s.setUser(u);
       // s.updateExpires();
        s.generateToken();
        s.save();
        return s;
    }

    /**
     * Deletes the session to log the user out
     */
    public void logout() {
        delete();
    }
}
