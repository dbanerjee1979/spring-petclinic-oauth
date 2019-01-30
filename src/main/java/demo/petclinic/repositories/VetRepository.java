package demo.petclinic.repositories;

import demo.petclinic.entities.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
