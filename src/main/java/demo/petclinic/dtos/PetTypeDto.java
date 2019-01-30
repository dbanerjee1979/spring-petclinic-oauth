package demo.petclinic.dtos;

import demo.petclinic.entities.PetType;

public class PetTypeDto extends PetType {
    private Long id;
    private String name;

    public PetTypeDto() {
    }

    public PetTypeDto(PetType petType) {
        this.id = petType.getId();
        this.name = petType.getName();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
