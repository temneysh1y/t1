package com.example.service;

import com.example.data.model.Technology;
import com.example.data.model.Characteristic;
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

class TechnologyCharacteristicServiceTest {

    @Mock
    private TechnologyRepository technologyRepository;

    @InjectMocks
    private TechnologyCharacteristicService technologyCharacteristicService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCharacteristics_TechnologyFound() {
        // Создаем объект Technology для теста
        Long techId = 1L;
        Technology existingTechnology = new Technology();
        existingTechnology.setId(techId);
        existingTechnology.setCharacteristics(new ArrayList<>());

        // Создаем новые характеристики и устанавливаем значения через сеттеры
        Characteristic osWindows = new Characteristic();
        osWindows.setName("Поддерживаемая ОС");
        osWindows.setValue("Windows");

        Characteristic osLinux = new Characteristic();
        osLinux.setName("Поддерживаемая ОС");
        osLinux.setValue("Linux");

        List<Characteristic> newCharacteristics = List.of(osWindows, osLinux);
        Technology technologyWithNewCharacteristics = new Technology();
        technologyWithNewCharacteristics.setId(techId);
        technologyWithNewCharacteristics.setCharacteristics(newCharacteristics);

        // Настраиваем mock-объект для возврата существующей технологии
        when(technologyRepository.findById(techId)).thenReturn(Optional.of(existingTechnology));

        // Вызываем метод addCharacteristics
        ResponseEntity<Object> response = technologyCharacteristicService.addCharacteristics(technologyWithNewCharacteristics);

        // Проверяем, что статус ответа OK и характеристики добавлены
        assertEquals(ResponseEntity.ok(existingTechnology), response);
        assertEquals(2, existingTechnology.getCharacteristics().size());

        // Проверяем, что save был вызван один раз
        verify(technologyRepository, times(1)).save(existingTechnology);
    }

    @Test
    void testAddCharacteristics_TechnologyNotFound() {
        // Настраиваем mock-объект для возврата пустого Optional
        Long techId = 2L;
        Technology technology = new Technology();
        technology.setId(techId);

        when(technologyRepository.findById(techId)).thenReturn(Optional.empty());

        // Вызываем метод addCharacteristics
        ResponseEntity<Object> response = technologyCharacteristicService.addCharacteristics(technology);

        // Проверяем, что статус ответа Not Found
        assertEquals(ResponseEntity.notFound().build(), response);

        // Проверяем, что save не был вызван
        verify(technologyRepository, never()).save(any(Technology.class));
    }
}
