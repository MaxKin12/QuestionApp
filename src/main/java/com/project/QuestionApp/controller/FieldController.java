package com.project.QuestionApp.controller;

import com.project.QuestionApp.dto.FieldDto;
import com.project.QuestionApp.entity.Field;
import com.project.QuestionApp.mapper.FieldMapper;
import com.project.QuestionApp.service.FieldService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@AllArgsConstructor
public class FieldController {
    @Autowired
    private FieldService fieldService;
    @Autowired
    private FieldMapper fieldMapper;

    @GetMapping("/fields")
    public List<FieldDto> getFields() {
        List<Field> fieldList = fieldService.findAllFields();
        return fieldMapper.toFieldDto(fieldList);
    }

    @PostMapping("/fields/create")
    public FieldDto createField(@RequestBody FieldDto fieldDto) {
        Field field = fieldService.createField(fieldMapper.toField(fieldDto));
        return fieldMapper.toFieldDto(field);
    }

    @PutMapping("/fields/update/{id}")
    public FieldDto updateField(@RequestBody FieldDto fieldDto, @PathVariable("id") Long fieldId) {
        Field field = fieldService.findById(fieldId);

        if (field != null) {
            fieldService.updateField(field, fieldMapper.toField(fieldDto));
            return fieldMapper.toFieldDto(field);
        } else {
            return null;
        }
    }

    @DeleteMapping("/fields/delete/{id}")
    public void deleteField(@PathVariable("id") Long fieldId) {
        fieldService.deleteField(fieldId);
    }
}
