package mx.com.pandadevs.pibeapi.models.aptitudes;
// Java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

// Lombok
import lombok.Getter;
import lombok.Setter;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;

// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "APTITUDES")
@Setter
@Getter
public class Aptitudes extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aptitudes")
    private Integer id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;

    // Relationships

    // VACANTS FAVORITES
    @ManyToMany(mappedBy = "aptitudes")
    private List<Resume> resumes = new ArrayList<>();

}