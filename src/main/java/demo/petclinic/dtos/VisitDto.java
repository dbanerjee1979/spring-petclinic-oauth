package demo.petclinic.dtos;

import demo.petclinic.entities.Visit;

import java.time.LocalDate;

public class VisitDto {
    private Long id;
    private LocalDate visitDate;
    private String details;

    public VisitDto() {
    }

    public VisitDto(Visit visit) {
        this.id = visit.getId();
        this.visitDate = visit.getVisitDate();
        this.details = visit.getDetails();
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
}
