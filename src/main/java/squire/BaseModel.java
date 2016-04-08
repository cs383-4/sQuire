package squire;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.sql.Timestamp;

/**
 * A base model for all other models to inherit from.
 * Contains Id, version, and created/updated timestamps.
 * From: https://github.com/ebean-orm-examples/avaje-ebeanorm-examples/blob/master/a-basic/src/main/java/org/example/domain/BaseModel.java
 */

@MappedSuperclass
public abstract class BaseModel extends Model {
    //Id is the primary key - the field which the databases index on. All models need to have this
    @Id
    Long id;

    /* Version is used to keep the models consistent. Ebean automatically checks this and increments it when
       saving a model, and doesn't allow saves if the new version number is <= the one in the database. This prevents
       multiple threads from overwriting the same row in the database. All models need to have this
     */
    @Version
    Long version;

    @CreatedTimestamp
    Timestamp whenCreated;

    @UpdatedTimestamp
    Timestamp whenUpdated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Timestamp getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Timestamp whenCreated) {
        this.whenCreated = whenCreated;
    }

    public Timestamp getWhenUpdated() {
        return whenUpdated;
    }

    public void setWhenUpdated(Timestamp whenUpdated) {
        this.whenUpdated = whenUpdated;
    }
}
