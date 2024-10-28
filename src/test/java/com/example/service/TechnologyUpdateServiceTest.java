package com.example.service;

import com.example.data.model.Characteristic;
import com.example.data.model.Technology;
import com.example.job.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TechnologyUpdateServiceTest {

    @Mock
    private TechnologyRepository technologyRepository;

    @InjectMocks
    private TechnologyUpdateService updateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateTechnology_Success() {
        Long techId = 1L;
        Technology existingTechnology = new Technology();
        existingTechnology.setId(techId);
        existingTechnology.setName("Old Technology");

        Technology updatedTechnology = new Technology();
        updatedTechnology.setId(techId);
        updatedTechnology.setName("Updated Technology");
        updatedTechnology.setCategory("New Category");
        updatedTechnology.setVersion("2.0");
        updatedTechnology.setDescription("Updated Description");
        updatedTechnology.setArchived(false);

        List<Characteristic> newCharacteristics = new ArrayList<>();
        Characteristic characteristic = new Characteristic();
        characteristic.setName("Characteristic 1");
        characteristic.setValue("Value 1");
        newCharacteristics.add(characteristic);
        updatedTechnology.setCharacteristics(newCharacteristics);

        when(technologyRepository.findById(techId)).thenReturn(Optional.of(existingTechnology));
        when(technologyRepository.save(existingTechnology)).thenReturn(existingTechnology);

        // Вызываем метод updateTechnology
        ResponseEntity<Object> response = updateService.updateTechnology(updatedTechnology);

        // Проверяем, что возвращается ResponseEntity с обновленной технологией
        assertEquals(ResponseEntity.ok(existingTechnology), response);
        assertEquals("Updated Technology", existingTechnology.getName());
        assertEquals("New Category", existingTechnology.getCategory());
        assertEquals("2.0", existingTechnology.getVersion());
        assertEquals("Updated Description", existingTechnology.getDescription());
        assertEquals(false, existingTechnology.getArchived());
        assertEquals(newCharacteristics, existingTechnology.getCharacteristics());

        // Проверяем, что метод save был вызван один раз
        verify(technologyRepository, times(1)).save(existingTechnology);
    }

    @Test
    void testUpdateTechnology_NotFound() {
        Long techId = 2L;
        Technology updatedTechnology = new Technology();
        updatedTechnology.setId(techId);

        // Настраиваем mock для findById, чтобы вернуть пустой Optional
        when(technologyRepository.findById(techId)).thenReturn(Optional.empty());

        // Вызываем метод updateTechnology
        ResponseEntity<Object> response = updateService.updateTechnology(updatedTechnology);

        // Проверяем, что возвращается статус NOT FOUND
        assertEquals(ResponseEntity.notFound().build(), response);

        // Проверяем, что метод save не был вызван
        verify(technologyRepository, never()).save(any(Technology.class));
    }
}
