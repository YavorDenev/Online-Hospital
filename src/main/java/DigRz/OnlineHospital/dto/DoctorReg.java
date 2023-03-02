package DigRz.OnlineHospital.dto;

import DigRz.OnlineHospital.constants.Specialty;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Service
public class DoctorReg {

    @NotNull
    @Size(min=2, max=30)
    @Column(length = 30, nullable = false)
    private String firstName;

    @NotNull
    @Size(min=2, max=30)
    @Column(length = 30, nullable = false)
    private String lastName;

    @NotNull
    @Column(length = 100, nullable = false)
    private String specialty;

    @NotNull
    @Size(min=5, max=30)
    @Column(length = 30, nullable = false)
    private String username;

    @NotNull
    @Size(min=5, max=100)
    @Column(length = 100, nullable = false)
    private String password;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
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
}
