package com.example.service;

import com.example.data.model.Technology;
import com.example.job.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TechnologyGetServiceTest {

    @Mock
    private TechnologyRepository technologyRepository;

    @InjectMocks
    private TechnologyGetService technologyGetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllActiveTechnologies() {
        // Создаем список активных технологий для теста
        Technology tech1 = new Technology();
        tech1.setId(1L);
        tech1.setName("Java");

        Technology tech2 = new Technology();
        tech2.setId(2L);
        tech2.setName("Python");

        List<Technology> expectedTechnologies = Arrays.asList(tech1, tech2);

        // Настраиваем mock-объект для возврата списка активных технологий
        when(technologyRepository.findByArchivedFalse()).thenReturn(expectedTechnologies);

        // Вызываем метод getAllActiveTechnologies
        ResponseEntity<Object> response = technologyGetService.getAllActiveTechnologies();

        // Проверяем, что статус ответа OK и возвращаемый список соответствует ожидаемому
        assertEquals(ResponseEntity.ok(expectedTechnologies), response);
    }
}
