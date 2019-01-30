package demo.petclinic.owners.controllers;

import demo.petclinic.owners.dtos.PetTypeDto;
import demo.petclinic.owners.entities.PetType;
import demo.petclinic.repositories.PetTypeRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/pets")
public class PetController {
    private PetTypeRepository petTypeRepository;

    public PetController(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @GetMapping(value = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PetType> findPetTypes() {
        return StreamSupport.stream(petTypeRepository.findAll().spliterator(), false)
                .map(PetTypeDto::new)
                .collect(toList());
    }
}
