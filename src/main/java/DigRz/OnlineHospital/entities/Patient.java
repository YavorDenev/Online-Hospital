package DigRz.OnlineHospital.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="patients")
public class Patient {

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
    @Min(0)
    @Max(100)
    @Column(nullable = false)
    private Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
