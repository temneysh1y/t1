package com.example.service;

import com.example.data.model.Characteristic;
import com.example.data.model.Technology;
import com.example.job.TechnologyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologyUpdateService {

    private final TechnologyRepository technologyRepository;

    public TechnologyUpdateService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public ResponseEntity<Object> updateTechnology(Technology technology) {
        Optional<Technology> existingTechnology = technologyRepository.findById(technology.getId());

        if (existingTechnology.isPresent()) {
            Technology techToUpdate = existingTechnology.get();
            techToUpdate.setName(technology.getName());
            techToUpdate.setCategory(technology.getCategory());
            techToUpdate.setVersion(technology.getVersion());
            techToUpdate.setDescription(technology.getDescription());
            techToUpdate.setArchived(technology.getArchived());


            List<Characteristic> newCharacteristics = technology.getCharacteristics();
            techToUpdate.getCharacteristics().clear();
            techToUpdate.getCharacteristics().addAll(newCharacteristics);

            technologyRepository.save(techToUpdate);
            return ResponseEntity.ok(techToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
