package com.example.service;

import com.example.data.model.Technology;
import com.example.job.TechnologyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechnologyUnarchiveService {
    private final TechnologyRepository technologyRepository;

    public TechnologyUnarchiveService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public ResponseEntity<Object> unarchiveTechnology(Long id) {
        Optional<Technology> techToArchive = technologyRepository.findById(id);
        if (techToArchive.isPresent()) {
            Technology tech = techToArchive.get();
            tech.setArchived(false);
            technologyRepository.save(tech);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
