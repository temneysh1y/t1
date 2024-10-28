package com.example.job;

import com.example.data.model.Technology;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TechnologyRequestTest {

    @Test
    public void testGettersAndSetters() {
        TechnologyRequest request = new TechnologyRequest();
        request.setAction("create");

        Technology tech = new Technology();
        tech.setName("Java");
        request.setTechnology(tech);

        assertThat(request.getAction()).isEqualTo("create");
        assertThat(request.getTechnology()).isEqualTo(tech);
    }
}
