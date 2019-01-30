package demo.petclinic.repositories;

import demo.petclinic.owners.entities.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
