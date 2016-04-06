package squire.Users;

import squire.BaseModel;
import squire.Users.query.QSession;
import squire.Users.query.QUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "o_session")
public class Session extends BaseModel {
    public static final SessionFinder find = new SessionFinder();
    private static final long SESSION_LENGTH = 7 * 24 * 60 * 60 * 1000; //1wk

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

    public boolean isExpired() {
        Timestamp now = new Timestamp(new Date().getTime());
        return now.after(expires);
    }

    private void updateExpires() {
        this.expires = new Timestamp(new Date().getTime() + SESSION_LENGTH);
    }

    public Session(User u) {
        super();
        setUser(u);
        updateExpires();
        generateToken();
        this.save();
    }
}
