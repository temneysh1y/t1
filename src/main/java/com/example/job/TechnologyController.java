package com.example.job;

import com.example.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TechnologyController {

    private final TechnologyService technologyService;

    @Autowired
    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping("/api/technologies/")
    public ResponseEntity<Object> handleTechnologyRequest(@RequestBody TechnologyRequest request) {
    return technologyService.processRequest(request);

    }
}
