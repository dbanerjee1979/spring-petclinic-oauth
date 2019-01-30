package demo.petclinic.config;

import demo.petclinic.entities.*;
import demo.petclinic.repositories.OwnerRepository;
import demo.petclinic.repositories.PetTypeRepository;
import demo.petclinic.repositories.SpecialtyRepository;
import demo.petclinic.repositories.VetRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class CannedData implements ApplicationRunner  {
    private VetRepository vetRepository;
    private SpecialtyRepository specialtyRepository;
    private PetTypeRepository petTypeRepository;
    private OwnerRepository ownerRepository;

    public CannedData(VetRepository vetRepository, SpecialtyRepository specialtyRepository,
                      PetTypeRepository petTypeRepository, OwnerRepository ownerRepository) {
        this.vetRepository = vetRepository;
        this.specialtyRepository = specialtyRepository;
        this.petTypeRepository = petTypeRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        Specialty radiology = specialtyRepository.save(new Specialty("radiology"));
        Specialty surgery = specialtyRepository.save(new Specialty("surgery"));
        Specialty dentistry = specialtyRepository.save(new Specialty("dentistry"));

        vetRepository.saveAll(Arrays.asList(
                new Vet("James", "Carter"),
                new Vet("Helen", "Leary", radiology),
                new Vet("Linda", "Douglas", surgery, dentistry),
                new Vet("Rafael", "Ortega", surgery),
                new Vet("Henry", "Stevens", radiology),
                new Vet("Sharon", "Jenkins")));

        PetType cat = petTypeRepository.save(new PetType("cat"));
        PetType dog = petTypeRepository.save(new PetType("dog"));
        PetType lizard = petTypeRepository.save(new PetType("lizard"));
        PetType snake = petTypeRepository.save(new PetType("snake"));
        PetType bird = petTypeRepository.save(new PetType("bird"));
        PetType hamster = petTypeRepository.save(new PetType("hamster"));

        ownerRepository.saveAll(Arrays.asList(
            new Owner("George", "Franklin", "110 W. Liberty St.", "Madison", "6085551023",
                    new Pet("Leo", LocalDate.parse("2010-09-07"), cat)),
            new Owner("Betty", "Davis", "638 Cardinal Ave.", "Sun Prairie", "6085551749",
                    new Pet("Basil", LocalDate.parse("2012-08-06"), hamster)),
            new Owner("Eduardo", "Rodriquez", "2693 Commerce St.", "McFarland", "6085558763",
                    new Pet("Rosy", LocalDate.parse("2011-04-17"), dog),
                    new Pet("Jewel", LocalDate.parse("2010-03-07"), dog)),
            new Owner("Harold", "Davis", "563 Friendly St.", "Windsor", "6085553198",
                    new Pet("Iggy", LocalDate.parse("2010-11-30"), lizard)),
            new Owner("Peter", "McTavish", "2387 S. Fair Way", "Madison", "6085552765",
                    new Pet("George", LocalDate.parse("2010-01-20"), snake)),
            new Owner("Jean", "Coleman", "105 N. Lake St.", "Monona", "6085552654",
                    new Pet("Samantha", LocalDate.parse("2010-01-20"), cat,
                            new Visit(LocalDate.parse("2013-01-01"), "rabies shot"),
                            new Visit(LocalDate.parse("2013-01-04"), "spayed")),
                    new Pet("Max", LocalDate.parse("2012-09-04"), cat,
                            new Visit(LocalDate.parse("2013-01-02"), "rabies shot"),
                            new Visit(LocalDate.parse("2013-01-03"), "neutered"))),
            new Owner("Jeff", "Black", "1450 Oak Blvd.", "Monona", "6085555387",
                    new Pet("Lucky", LocalDate.parse("2011-08-06"), bird)),
            new Owner("Maria", "Escobito", "345 Maple St.", "Madison", "6085557683",
                    new Pet("Mulligan", LocalDate.parse("2007-02-24"), dog)),
            new Owner("David", "Schroeder", "2749 Blackhawk Trail", "Madison", "6085559435",
                    new Pet("Freddy", LocalDate.parse("2010-03-09"), bird)),
            new Owner("Carlos", "Estaban", "2335 Independence La.", "Waunakee", "6085555487",
                    new Pet("Lucky", LocalDate.parse("2010-06-24"), dog),
                    new Pet("Sly", LocalDate.parse("2012-06-08"), cat))
        ));
    }
}
