package demo.petclinic.veterinarians.config;

import demo.petclinic.veterinarians.entities.Specialty;
import demo.petclinic.veterinarians.entities.Vet;
import demo.petclinic.veterinarians.repositories.SpecialtyRepository;
import demo.petclinic.veterinarians.repositories.VetRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class VetsCannedData implements ApplicationRunner {
    private SpecialtyRepository specialtyRepository;
    private VetRepository vetRepository;

    public VetsCannedData(SpecialtyRepository specialtyRepository, VetRepository vetRepository) {
        this.specialtyRepository = specialtyRepository;
        this.vetRepository = vetRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
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
    }
}
