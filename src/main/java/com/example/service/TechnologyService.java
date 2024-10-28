package com.example.service;

import com.example.data.model.Technology;
import com.example.job.TechnologyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TechnologyService {

    private final TechnologyGetService getService;
    private final TechnologyAddService addService;
    private final TechnologyUpdateService updateService;
    private final TechnologyArchiveService archiveService;
    private final TechnologyCharacteristicService characteristicService;
    private final TechnologyUnarchiveService unarchiveService;

    public TechnologyService(TechnologyGetService getService,
                             TechnologyAddService addService,
                             TechnologyUpdateService updateService,
                             TechnologyArchiveService archiveService,
                             TechnologyCharacteristicService characteristicService,
                             TechnologyUnarchiveService unarchiveService) {
        this.getService = getService;
        this.addService = addService;
        this.updateService = updateService;
        this.archiveService = archiveService;
        this.characteristicService = characteristicService;
        this.unarchiveService = unarchiveService;
    }

    public ResponseEntity<Object> processRequest(TechnologyRequest request) {
        String action = request.getAction().toLowerCase();
        Technology technology = request.getTechnology();

        switch (action) {
            case "get":
                return getService.getAllActiveTechnologies();
            case "add":
                return addService.addTechnology(technology);
            case "update":
                return updateService.updateTechnology(technology);
            case "archive":
                return archiveService.archiveTechnology(technology.getId());
            case "unarchive":
                return unarchiveService.unarchiveTechnology(technology.getId());
            case "add-characteristic":
                return characteristicService.addCharacteristics(technology);
            default:
                return ResponseEntity.badRequest().body("Invalid action");
        }
    }
}

