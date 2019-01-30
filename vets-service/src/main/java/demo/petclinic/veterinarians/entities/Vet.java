package demo.petclinic.veterinarians.entities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Specialty> specialties;

    public Vet() {
    }

    public Vet(String firstName, String lastName, Specialty... specialties) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialties = new HashSet<>(Arrays.asList(specialties));
    }

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

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}
