package com.project.QuestionApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.QuestionApp.enums.FieldType;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldDto {
    private Long id;
    private String label;
    private boolean isRequired;
    private boolean isActive;
    private FieldType type;
    private List<OptionsDto> options;
}
