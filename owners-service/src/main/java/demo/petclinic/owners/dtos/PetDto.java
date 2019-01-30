package demo.petclinic.owners.dtos;

import demo.petclinic.owners.entities.Pet;

import java.time.LocalDate;

public class PetDto {
    private String name;
    private LocalDate birthdate;
    private PetTypeDto petType;

    public PetDto() {
    }

    public PetDto(Pet pet) {
        this.name = pet.getName();
        this.birthdate = pet.getBirthdate();
        this.petType = new PetTypeDto(pet.getPetType());
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

    public PetTypeDto getPetType() {
        return petType;
    }

    public void setPetType(PetTypeDto petType) {
        this.petType = petType;
    }
}
