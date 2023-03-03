package DigRz.OnlineHospital.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
    private Long id;

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
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
