package demo.petclinic.veterinarians.repositories;

import demo.petclinic.veterinarians.entities.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
