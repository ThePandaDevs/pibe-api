package mx.com.pandadevs.pibeapi.models.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    private Long id;
    @NotNull
    @NotBlank
    @Size(
        min = 5,
        max = 50)
    @Email
    private String email;

    @JsonIgnore
    @NotNull
    @NotBlank
    @Size(
        min = 5,
        max = 100)
    private String password;

    private Boolean active;


    // Getters & Setters
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

    
}