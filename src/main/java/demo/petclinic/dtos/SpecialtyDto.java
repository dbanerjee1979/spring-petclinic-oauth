package demo.petclinic.dtos;

import demo.petclinic.entities.Specialty;

public class SpecialtyDto {
    private Long id;
    private String name;

    public SpecialtyDto(Specialty specialty) {
        this.id = specialty.getId();
        this.name = specialty.getName();
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
