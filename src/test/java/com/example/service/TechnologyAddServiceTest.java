package com.example.service;

import com.example.data.model.Technology;
import com.example.job.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TechnologyAddServiceTest {

    @Mock
    private TechnologyRepository technologyRepository;

    @InjectMocks
    private TechnologyAddService technologyAddService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTechnology() {
        // Создаем объект Technology для тестирования
        Technology technology = new Technology();
        technology.setId(1L);
        technology.setName("Java");

        // Настраиваем mock-объект для возврата созданного объекта
        when(technologyRepository.save(any(Technology.class))).thenReturn(technology);

        // Вызываем метод и проверяем результат
        ResponseEntity<Object> response = technologyAddService.addTechnology(technology);

        // Проверяем, что статус ответа OK и возвращаемый объект совпадает с ожидаемым
        assertEquals(ResponseEntity.ok(technology), response);
    }
}
