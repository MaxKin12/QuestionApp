package com.project.QuestionApp.service;

import com.project.QuestionApp.entity.Field;
import com.project.QuestionApp.repository.FieldRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private OptionsService optionsService;

    public Field createField(Field field) {
        return fieldRepository.save(field);
    }

    public Field updateField(Field field, Field updatedField) {
        field.setType(updatedField.getType());
        field.setLabel(updatedField.getLabel());
        field.setActive(updatedField.isActive());
        field.setRequired(updatedField.isActive());
        optionsService.update(field.getOptions(), updatedField.getOptions());
        fieldRepository.save(field);

        return field;
    }

    public void deleteField(Long fieldId) {
        Field field = fieldRepository.findById(fieldId).orElse(null);

        if (field != null) {
            fieldRepository.delete(field);
        }
    }

    public List<Field> findAllFields() {
        return fieldRepository.findAll();
    }

    public Field findById(Long id) {
        return fieldRepository.findById(id).orElse(null);
    }
}

