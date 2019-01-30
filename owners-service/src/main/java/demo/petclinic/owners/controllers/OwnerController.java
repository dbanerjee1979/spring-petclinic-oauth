package demo.petclinic.owners.controllers;

import demo.petclinic.owners.dtos.*;
import demo.petclinic.owners.entities.Owner;
import demo.petclinic.owners.entities.Pet;
import demo.petclinic.owners.entities.Visit;
import demo.petclinic.owners.repositories.OwnerRepository;
import demo.petclinic.owners.repositories.PetRepository;
import demo.petclinic.owners.repositories.PetTypeRepository;
import demo.petclinic.owners.repositories.VisitRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.function.Supplier;

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OwnerDto create(@RequestBody OwnerDto owner) {
        return new OwnerDto(ownerRepository.save(new Owner(owner)));
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerDto> update(@PathVariable Long id, @RequestBody OwnerDto owner, Principal principal) {
        Authentication auth = (Authentication) principal;
        return ownerRepository.findById(id)
                .map(existingOwner -> check(existingOwner, auth, () ->
                        ResponseEntity.ok(new OwnerDto(ownerRepository.save(existingOwner.merge(owner))))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/{id}/pets", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PetDto> createPet(@PathVariable long id, @RequestBody PetDto pet) {
        return ownerRepository.findById(id)
                .map(owner -> new PetDto(petRepository.save(new Pet(pet, owner, petTypeRepository))))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @PutMapping(path = "/{ownerId}/pets/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PetDto> updatePet(@PathVariable long ownerId, @PathVariable long id, @RequestBody PetDto pet,
                                            Principal principal) {
        Authentication auth = (Authentication) principal;
        return ownerRepository.findById(ownerId)
                .flatMap(existingOwner -> check(existingOwner, auth, () ->
                    petRepository.findById(id)
                        .map(existing -> existing.merge(pet, petTypeRepository))
                        .map(petRepository::save)
                        .map(PetDto::new)
                        .map(ResponseEntity::ok)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @PostMapping(path = "/{ownerId}/pets/{id}/visits", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitDto> createVisit(@PathVariable long ownerId, @PathVariable long id, @RequestBody VisitDto visit,
                                                Principal principal) {
        Authentication auth = (Authentication) principal;
        return ownerRepository.findById(ownerId)
                .flatMap(existingOwner -> check(existingOwner, auth, () ->
                    petRepository.findById(id)
                        .map(pet -> visitRepository.save(new Visit(visit, pet)))
                        .map(VisitDto::new)
                        .map(ResponseEntity::ok)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private <T> T check(Owner owner, Authentication auth, Supplier<T> action) {
        if (owner.getUsername().equals(auth.getName())) {
            return action.get();
        }
        else {
            throw new AccessDeniedException("Access denied");
        }
    }
}
