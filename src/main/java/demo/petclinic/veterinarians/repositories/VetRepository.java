package demo.petclinic.veterinarians.repositories;

import demo.petclinic.veterinarians.entities.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
