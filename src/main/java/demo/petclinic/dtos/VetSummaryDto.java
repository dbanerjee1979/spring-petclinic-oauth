package demo.petclinic.dtos;

import demo.petclinic.entities.Vet;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class VetSummaryDto {
    private Long id;
    private String firstName;
    private String lastName;
    private List<SpecialtyDto> specialties;

    public VetSummaryDto(Vet vet) {
        id = vet.getId();
        firstName = vet.getFirstName();
        lastName = vet.getLastName();
        specialties = vet.getSpecialties().stream().map(SpecialtyDto::new).collect(toList());
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

    public List<SpecialtyDto> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<SpecialtyDto> specialties) {
        this.specialties = specialties;
    }
}
