package com.example.service;

import com.example.data.model.Technology;
import com.example.job.TechnologyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TechnologyServiceTest {

    @Mock
    private TechnologyGetService getService;

    @Mock
    private TechnologyAddService addService;

    @Mock
    private TechnologyUpdateService updateService;

    @Mock
    private TechnologyArchiveService archiveService;

    @Mock
    private TechnologyCharacteristicService characteristicService;

    @Mock
    private TechnologyUnarchiveService unarchiveService;

    @InjectMocks
    private TechnologyService technologyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessRequest_GetAction() {
        TechnologyRequest request = new TechnologyRequest();
        request.setAction("get");

        // Настраиваем mock для getService
        when(getService.getAllActiveTechnologies()).thenReturn(ResponseEntity.ok("Active Technologies"));

        // Вызываем метод processRequest
        ResponseEntity<Object> response = technologyService.processRequest(request);

        // Проверяем, что возвращается правильный ответ
        assertEquals(ResponseEntity.ok("Active Technologies"), response);
    }

    @Test
    void testProcessRequest_AddAction() {
        TechnologyRequest request = new TechnologyRequest();
        request.setAction("add");
        Technology technology = new Technology();
        request.setTechnology(technology);

        // Настраиваем mock для addService
        when(addService.addTechnology(technology)).thenReturn(ResponseEntity.ok("Technology added"));

        // Вызываем метод processRequest
        ResponseEntity<Object> response = technologyService.processRequest(request);

        // Проверяем, что возвращается правильный ответ
        assertEquals(ResponseEntity.ok("Technology added"), response);
    }

    @Test
    void testProcessRequest_UpdateAction() {
        TechnologyRequest request = new TechnologyRequest();
        request.setAction("update");
        Technology technology = new Technology();
        request.setTechnology(technology);

        // Настраиваем mock для updateService
        when(updateService.updateTechnology(technology)).thenReturn(ResponseEntity.ok("Technology updated"));

        // Вызываем метод processRequest
        ResponseEntity<Object> response = technologyService.processRequest(request);

        // Проверяем, что возвращается правильный ответ
        assertEquals(ResponseEntity.ok("Technology updated"), response);
    }

    @Test
    void testProcessRequest_ArchiveAction() {
        TechnologyRequest request = new TechnologyRequest();
        request.setAction("archive");
        Technology technology = new Technology();
        technology.setId(1L);
        request.setTechnology(technology);

        // Настраиваем mock для archiveService
        when(archiveService.archiveTechnology(1L)).thenReturn(ResponseEntity.noContent().build());

        // Вызываем метод processRequest
        ResponseEntity<Object> response = technologyService.processRequest(request);

        // Проверяем, что возвращается правильный ответ
        assertEquals(ResponseEntity.noContent().build(), response);
    }


    @Test
    void testProcessRequest_UnarchiveAction() {
        TechnologyRequest request = new TechnologyRequest();
        request.setAction("unarchive");
        Technology technology = new Technology();
        technology.setId(1L);
        request.setTechnology(technology);

        // Настраиваем mock для unarchiveService
        when(unarchiveService.unarchiveTechnology(1L)).thenReturn(ResponseEntity.noContent().build());

        // Вызываем метод processRequest
        ResponseEntity<Object> response = technologyService.processRequest(request);

        // Проверяем, что возвращается правильный ответ
        assertEquals(ResponseEntity.noContent().build(), response);
    }


    @Test
    void testProcessRequest_AddCharacteristicAction() {
        TechnologyRequest request = new TechnologyRequest();
        request.setAction("add-characteristic");
        Technology technology = new Technology();
        request.setTechnology(technology);

        // Настраиваем mock для characteristicService
        when(characteristicService.addCharacteristics(technology)).thenReturn(ResponseEntity.ok("Characteristics added"));

        // Вызываем метод processRequest
        ResponseEntity<Object> response = technologyService.processRequest(request);

        // Проверяем, что возвращается правильный ответ
        assertEquals(ResponseEntity.ok("Characteristics added"), response);
    }

    @Test
    void testProcessRequest_InvalidAction() {
        TechnologyRequest request = new TechnologyRequest();
        request.setAction("invalid-action");

        // Вызываем метод processRequest
        ResponseEntity<Object> response = technologyService.processRequest(request);

        // Проверяем, что возвращается ошибка 400
        assertEquals(ResponseEntity.badRequest().body("Invalid action"), response);
    }
}
