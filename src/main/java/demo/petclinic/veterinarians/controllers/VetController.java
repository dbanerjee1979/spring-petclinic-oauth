package demo.petclinic.veterinarians.controllers;

import demo.petclinic.veterinarians.dtos.VetSummaryDto;
import demo.petclinic.veterinarians.repositories.VetRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/vets")
public class VetController {
    private VetRepository vetRepository;

    public VetController(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VetSummaryDto> findAll() {
        return StreamSupport.stream(vetRepository.findAll().spliterator(), false)
                .map(VetSummaryDto::new)
                .collect(toList());
    }
}
