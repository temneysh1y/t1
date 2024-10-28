package com.example.service;

import com.example.data.model.Technology;
import com.example.job.TechnologyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechnologyCharacteristicService {
    private final TechnologyRepository technologyRepository;

    public TechnologyCharacteristicService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public ResponseEntity<Object> addCharacteristics(Technology technology) {
        Optional<Technology> techToAddCharacteristic = technologyRepository.findById(technology.getId());
        if (techToAddCharacteristic.isPresent()) {
            Technology tech = techToAddCharacteristic.get();
            tech.getCharacteristics().addAll(technology.getCharacteristics());
            technologyRepository.save(tech);
            return ResponseEntity.ok(tech);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
