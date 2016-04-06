package squire.models;

import javax.persistence.*;
import com.avaje.ebean.Model;

@Entity
@Table(name = "o_user")
public class User extends Model {
    @Id
    Long id;

    @Version
    Long version;

    @Column(length = 200)
    private
    String username;

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
}
