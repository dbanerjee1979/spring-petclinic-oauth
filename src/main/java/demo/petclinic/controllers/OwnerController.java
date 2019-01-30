package demo.petclinic.controllers;

import demo.petclinic.dtos.*;
import demo.petclinic.entities.Owner;
import demo.petclinic.entities.Pet;
import demo.petclinic.entities.Visit;
import demo.petclinic.repositories.OwnerRepository;
import demo.petclinic.repositories.PetRepository;
import demo.petclinic.repositories.PetTypeRepository;
import demo.petclinic.repositories.VisitRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private OwnerRepository ownerRepository;
    private PetTypeRepository petTypeRepository;
    private PetRepository petRepository;
    private VisitRepository visitRepository;

    public OwnerController(OwnerRepository ownerRepository, PetTypeRepository petTypeRepository,
                           PetRepository petRepository, VisitRepository visitRepository) {
        this.ownerRepository = ownerRepository;
        this.petTypeRepository = petTypeRepository;
        this.petRepository = petRepository;
        this.visitRepository = visitRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OwnerSummaryDto> findAll(@RequestParam(defaultValue = "", required = false) String lastName) {
        return ownerRepository.findByLastNameIgnoreCaseContains(lastName)
                .stream()
                .map(OwnerSummaryDto::new)
                .collect(toList());
    }

    @GetMapping(path = "/{id}/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerDetailDto> findDetailsById(@PathVariable long id) {
        return ownerRepository.findDetailsById(id)
                .map(OwnerDetailDto::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OwnerDto create(@RequestBody OwnerDto owner) {
        return new OwnerDto(ownerRepository.save(new Owner(owner)));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerDto> update(@PathVariable Long id, @RequestBody OwnerDto owner) {
        return ownerRepository.findById(id)
                .map(existingOwner -> existingOwner.merge(owner))
                .map(ownerRepository::save)
                .map(OwnerDto::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/{id}/pets", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PetDto> createPet(@PathVariable long id, @RequestBody PetDto pet) {
        return ownerRepository.findById(id)
                .map(owner -> new PetDto(petRepository.save(new Pet(pet, owner, petTypeRepository))))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{ownerId}/pets/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PetDto> updatePet(@PathVariable long ownerId, @PathVariable long id, @RequestBody PetDto pet) {
        return !ownerRepository.existsById(ownerId) ?
                ResponseEntity.notFound().build() :
                petRepository.findById(id)
                    .map(existing -> existing.merge(pet, petTypeRepository))
                    .map(petRepository::save)
                    .map(PetDto::new)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/{ownerId}/pets/{id}/visits", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitDto> createVisit(@PathVariable long ownerId, @PathVariable long id, @RequestBody VisitDto visit) {
        return !ownerRepository.existsById(ownerId) ?
                ResponseEntity.notFound().build() :
                petRepository.findById(id)
                    .map(pet -> visitRepository.save(new Visit(visit, pet)))
                    .map(VisitDto::new)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
