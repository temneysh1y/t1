package com.example.service;

import com.example.data.model.Technology;
import com.example.job.TechnologyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class TechnologyAddService {

    private final TechnologyRepository technologyRepository;

    public TechnologyAddService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public ResponseEntity<Object> addTechnology(Technology technology) {
        Technology newTechnology = technologyRepository.save(technology);
        return ResponseEntity.ok(newTechnology);
    }
}
