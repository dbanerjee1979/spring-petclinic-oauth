package demo.petclinic.repositories;

import demo.petclinic.entities.Owner;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    @EntityGraph("Owner.withPets")
    List<Owner> findByLastNameIgnoreCaseContains(String lastName);

    @EntityGraph("Owner.withPetsAndVisits")
    @Query("from Owner where id = ?1")
    Optional<Owner> findDetailsById(long id);
}
