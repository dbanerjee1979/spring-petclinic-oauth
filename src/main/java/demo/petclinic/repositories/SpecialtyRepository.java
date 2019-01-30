package demo.petclinic.repositories;

import demo.petclinic.entities.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
