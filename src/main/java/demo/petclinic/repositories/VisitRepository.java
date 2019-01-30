package demo.petclinic.repositories;

import demo.petclinic.owners.entities.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
