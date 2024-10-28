package com.example.service;

import com.example.data.model.Technology;
import com.example.job.TechnologyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechnologyArchiveService {

    private final TechnologyRepository technologyRepository;

    public TechnologyArchiveService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public ResponseEntity<Object> archiveTechnology(Long id) {
        Optional<Technology> techToArchive = technologyRepository.findById(id);
        if (techToArchive.isPresent()) {
            Technology tech = techToArchive.get();
            tech.setArchived(true);
            technologyRepository.save(tech);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
