package com.project.QuestionApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionsDto {
    private String option;
    private Long fieldId;

    public OptionsDto() {
    }

    public OptionsDto(String option) {
        this.option = option;
    }
}