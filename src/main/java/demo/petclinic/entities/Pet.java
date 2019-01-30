package demo.petclinic.entities;

import demo.petclinic.dtos.PetDto;
import demo.petclinic.repositories.PetTypeRepository;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private LocalDate birthdate;

    @ManyToOne(fetch = FetchType.EAGER)
    private PetType petType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    @OrderBy("visitDate ASC")
    private List<Visit> visits;

    public Pet() {
    }

    public Pet(String name, LocalDate birthdate, PetType petType, Visit... visits) {
        this.name = name;
        this.birthdate = birthdate;
        this.petType = petType;
        this.visits = new ArrayList<>(Arrays.asList(visits));
        this.visits.stream().forEach(v -> v.setPet(this));
    }

    public Pet(PetDto pet, Owner owner, PetTypeRepository petTypeRepository) {
        merge(pet, petTypeRepository);
        this.owner = owner;
    }

    public Pet merge(PetDto pet, PetTypeRepository petTypeRepository) {
        this.name = pet.getName();
        this.birthdate = pet.getBirthdate();
        this.petType = petTypeRepository.findById(pet.getPetType().getId()).get();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public List<Visit> getVisits() {
        return visits;
    }
}
