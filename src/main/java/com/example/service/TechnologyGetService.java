package com.example.service;

import com.example.data.model.Technology;
import com.example.job.TechnologyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyGetService {

    private final TechnologyRepository repository;

    public TechnologyGetService(TechnologyRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Object> getAllActiveTechnologies() {
        List<Technology> technologies = repository.findByArchivedFalse();
        return ResponseEntity.ok(technologies);
    }
}
