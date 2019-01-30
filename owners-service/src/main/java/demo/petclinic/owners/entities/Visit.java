package demo.petclinic.owners.entities;

import demo.petclinic.owners.dtos.VisitDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDate visitDate;
    private String details;

    @ManyToOne
    private Pet pet;

    public Visit() {
    }

    public Visit(LocalDate visitDate, String details) {
        this.visitDate = visitDate;
        this.details = details;
    }

    public Visit(VisitDto visit, Pet pet) {
        this.visitDate = visit.getVisitDate();
        this.details = visit.getDetails();
        this.pet = pet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }
}
