package mx.com.pandadevs.pibeapi.models.notifications;

// Java
import java.io.Serializable;

// Persistence
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import mx.com.pandadevs.pibeapi.models.users.User;
// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "USERS_NOTIFICATIONS")
public class UserNotification extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private UserNotificationPK id;

    @Column(
        nullable = false,
        columnDefinition = "TEXT")
    private String action;

    @Column(
        nullable = false,
        columnDefinition = "tinyint default 0")
    private Boolean seen;

    // Relationship
    
    // User
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    // Notification
    @ManyToOne
    @JoinColumn(name = "notification_id", insertable = false, updatable = false)
    private Notification notification;

    // Getters & Setters
    public UserNotificationPK getId() {
        return id;
    }

    public void setId(UserNotificationPK id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    

}