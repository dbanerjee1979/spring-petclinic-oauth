package demo.petclinic.owners.entities;

import javax.persistence.*;

@Entity
@Table
public class PetType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    public PetType() {
    }

    public PetType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
