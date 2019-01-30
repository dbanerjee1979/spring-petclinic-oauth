package demo.petclinic.dtos;

import demo.petclinic.entities.Pet;
import demo.petclinic.entities.PetType;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PetDetailDto {
    private Long id;
    private String name;
    private LocalDate birthdate;
    private PetType petType;
    private List<VisitDto> visits;

    public PetDetailDto(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.birthdate = pet.getBirthdate();
        this.petType = new PetTypeDto(pet.getPetType());
        this.visits = pet.getVisits().stream().map(VisitDto::new).collect(toList());
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

    public List<VisitDto> getVisits() {
        return visits;
    }

    public void setVisits(List<VisitDto> visits) {
        this.visits = visits;
    }
}
