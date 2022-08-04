package mx.com.pandadevs.pibeapi.models.studies.dto;

// Validations
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class StudyDto {

    private Integer id;
    @NotEmpty(message = "Please provide a study")
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.0-9]*")
    @Size(min = 5, max = 40)
    private String name;
    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;
    // Getters && Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(LocalDateTime startPeriod) {
        this.startPeriod = startPeriod;
    }

    public LocalDateTime getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(LocalDateTime endPeriod) {
        this.endPeriod = endPeriod;
    }
}