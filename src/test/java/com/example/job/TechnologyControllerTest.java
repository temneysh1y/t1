package com.example.job;

import com.example.service.TechnologyService;
import com.example.data.model.Technology;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TechnologyController.class)
public class TechnologyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TechnologyService technologyService;

    @Test
    public void testHandleTechnologyRequest() throws Exception {
        TechnologyRequest request = new TechnologyRequest();
        request.setAction("create");
        request.setTechnology(new Technology());

        Mockito.when(technologyService.processRequest(Mockito.any())).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(post("/api/technologies/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"action\": \"create\", \"technology\": { \"name\": \"Java\" } }"))
                .andExpect(status().isOk());
    }
}
