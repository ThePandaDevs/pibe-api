package mx.com.pandadevs.pibeapi.models.users;

// Java
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;


// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.logs.Log;
import mx.com.pandadevs.pibeapi.models.notifications.UserNotification;
import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.roles.Role;
import mx.com.pandadevs.pibeapi.models.vacants.UserVacant;
import mx.com.pandadevs.pibeapi.models.vacants.Vacant;

@Entity
@Table(name = "USERS")
public class User extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(50)")
    private String email;

    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(50)")
    private String username;

    @Column(
        nullable = false,
        columnDefinition = "varchar(100)")
    private String password;

    @Column(
        nullable = false,
        columnDefinition = "tinyint default 1")
    private Boolean active;

    @Column(
        name = "link_restore_password",
        unique = true, columnDefinition = "varchar(150)")
    private String linkRestorePassword;

    @Column(
        name = "link_activate_email",
        unique = true, columnDefinition = "varchar(150)")
    private String linkActivateEmail;

    // Relationships

    // Roles
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "USERS_ROLES",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public void addRole() {
        roles = new HashSet<>();
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    // Notifications
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<UserNotification> notifications;

    // Profile
    @OneToOne(mappedBy="user")

    private Profile profile;

    // Vacants
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<Vacant> vacants;

    // vacants favorites
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "VACANTS_FAVORITES",
            joinColumns = @JoinColumn(name = "vacant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Vacant> favoitesVacants;

    public void addToFavorite(Vacant vacant) {
        favoitesVacants.add(vacant);
        vacant.getUsers().add(this);
    }

    public void removeFromFavorite(Vacant vacant) {
        favoitesVacants.remove(vacant);
        vacant.getUsers().remove(this);
    }

    // User Vacants
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<UserVacant> userVacants;

    // Logs

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<Log> logs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLinkRestorePassword() {
        return linkRestorePassword;
    }

    public void setLinkRestorePassword(String linkRestorePassword) {
        this.linkRestorePassword = linkRestorePassword;
    }

    public String getLinkActivateEmail() {
        return linkActivateEmail;
    }

    public void setLinkActivateEmail(String linkActivateEmail) {
        this.linkActivateEmail = linkActivateEmail;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Set<UserNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<UserNotification> notifications) {
        this.notifications = notifications;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Vacant> getVacants() {
        return vacants;
    }

    public void setVacants(Set<Vacant> vacants) {
        this.vacants = vacants;
    }

    public List<Vacant> getFavoitesVacants() {
        return favoitesVacants;
    }

    public void setFavoitesVacants(List<Vacant> favoitesVacants) {
        this.favoitesVacants = favoitesVacants;
    }

    public Set<UserVacant> getUserVacants() {
        return userVacants;
    }

    public void setUserVacants(Set<UserVacant> userVacants) {
        this.userVacants = userVacants;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }
}
