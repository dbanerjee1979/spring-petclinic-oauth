package demo.petclinic.repositories;

import demo.petclinic.owners.entities.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
