package demo.petclinic.owners.dtos;

import demo.petclinic.owners.entities.Pet;

public class PetIdDto {
    private Long id;
    private String name;

    public PetIdDto(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
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
}
