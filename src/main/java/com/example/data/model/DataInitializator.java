package com.example.data.model;
import com.example.job.TechnologyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializator implements CommandLineRunner {

    private final TechnologyRepository technologyRepository;

    public DataInitializator(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (technologyRepository.count() == 0) {
            Technology javaTech = new Technology();
            javaTech.setName("Java");
            javaTech.setCategory("Язык программирования");
            javaTech.setVersion("17");
            javaTech.setDescription("Объектно-ориентированный язык программирования");

            Characteristic osCharacteristic = new Characteristic();
            osCharacteristic.setName("Поддерживаемая ОС");
            osCharacteristic.setValue("Windows, Linux, macOS");

            javaTech.getCharacteristics().add(osCharacteristic);

            technologyRepository.save(javaTech);
        }
    }
}
