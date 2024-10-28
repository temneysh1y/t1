package com.example.job;

import com.example.data.model.Technology;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TechnologyRequest {
    private String action;
    private Technology technology;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

}

