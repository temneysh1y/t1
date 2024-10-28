package com.example.service;

import com.example.data.model.Technology;
import com.example.job.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TechnologyArchiveServiceTest {

    @Mock
    private TechnologyRepository technologyRepository;

    @InjectMocks
    private TechnologyArchiveService technologyArchiveService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testArchiveTechnology_TechnologyFound() {
        // Создаем объект Technology для теста
        Long techId = 1L;
        Technology technology = new Technology();
        technology.setId(techId);
        technology.setArchived(false);

        // Настраиваем mock-объект для возврата технологии
        when(technologyRepository.findById(techId)).thenReturn(Optional.of(technology));

        // Вызываем метод archiveTechnology
        ResponseEntity<Object> response = technologyArchiveService.archiveTechnology(techId);

        // Проверяем, что статус ответа No Content и технология заархивирована
        assertEquals(ResponseEntity.noContent().build(), response);
        assertEquals(true, technology.getArchived());

        // Проверяем, что save был вызван один раз
        verify(technologyRepository, times(1)).save(technology);
    }

    @Test
    void testArchiveTechnology_TechnologyNotFound() {
        // Настраиваем mock-объект для возврата пустого Optional
        Long techId = 2L;
        when(technologyRepository.findById(techId)).thenReturn(Optional.empty());

        // Вызываем метод archiveTechnology
        ResponseEntity<Object> response = technologyArchiveService.archiveTechnology(techId);

        // Проверяем, что статус ответа Not Found
        assertEquals(ResponseEntity.notFound().build(), response);

        // Проверяем, что save не был вызван
        verify(technologyRepository, never()).save(any(Technology.class));
    }
}
