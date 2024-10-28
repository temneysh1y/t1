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

class TechnologyUnarchiveServiceTest {

    @Mock
    private TechnologyRepository technologyRepository;

    @InjectMocks
    private TechnologyUnarchiveService unarchiveService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUnarchiveTechnology_Success() {
        Long techId = 1L;
        Technology technology = new Technology();
        technology.setId(techId);
        technology.setArchived(true);

        // Настраиваем mock, чтобы вернуть технологию, если она существует
        when(technologyRepository.findById(techId)).thenReturn(Optional.of(technology));

        // Вызываем метод unarchiveTechnology
        ResponseEntity<Object> response = unarchiveService.unarchiveTechnology(techId);

        // Проверяем, что технология была обновлена и что возвращен корректный статус
        assertEquals(ResponseEntity.noContent().build(), response);
        assertEquals(false, technology.getArchived());

        // Проверяем, что метод save был вызван один раз
        verify(technologyRepository, times(1)).save(technology);
    }

    @Test
    void testUnarchiveTechnology_NotFound() {
        Long techId = 2L;

        // Настраиваем mock, чтобы вернуть пустой Optional, если технология не найдена
        when(technologyRepository.findById(techId)).thenReturn(Optional.empty());

        // Вызываем метод unarchiveTechnology
        ResponseEntity<Object> response = unarchiveService.unarchiveTechnology(techId);

        // Проверяем, что возвращен статус NOT FOUND
        assertEquals(ResponseEntity.notFound().build(), response);

        // Проверяем, что метод save не был вызван
        verify(technologyRepository, never()).save(any(Technology.class));
    }
}
