package com.project.QuestionApp.mapper;

import com.project.QuestionApp.dto.FieldDto;
import com.project.QuestionApp.dto.OptionsDto;
import com.project.QuestionApp.entity.Field;
import com.project.QuestionApp.entity.Options;
import com.project.QuestionApp.enums.FieldType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FieldMapper {
    public Field toField(FieldDto fieldDto) {
        Field field = new Field();
        field.setId(fieldDto.getId());
        field.setLabel(fieldDto.getLabel());
        field.setActive(fieldDto.isActive());
        field.setRequired(fieldDto.isRequired());
        field.setType(fieldDto.getType().toString());

        List<Options> optionsList = new ArrayList<>();
        for (OptionsDto optionsDto: fieldDto.getOptions()) {
            Options options = new Options();
            options.setOption(optionsDto.getOption());
            options.setField(field);
            optionsList.add(options);
        }
        field.setOptions(optionsList);
        return field;
    }

    public FieldDto toFieldDto(Field field) {
        FieldDto fieldDto = new FieldDto();
        fieldDto.setId(field.getId());
        fieldDto.setLabel(field.getLabel());
        fieldDto.setActive(field.isActive());
        fieldDto.setRequired(field.isRequired());
        fieldDto.setType(FieldType.valueOf(field.getType()));

        List<OptionsDto> optionsDtoList = new ArrayList<>();
        for (Options options: field.getOptions()) {
            OptionsDto optionsDto = new OptionsDto();
            optionsDto.setOption(options.getOption());
            optionsDto.setFieldId(field.getId());
            optionsDtoList.add(optionsDto);
        }
        fieldDto.setOptions(optionsDtoList);
        return fieldDto;
    }

    public List<FieldDto> toFieldDto(List<Field> fieldList) {
        List<FieldDto> fieldDtoList = new ArrayList<>();
        for (Field field: fieldList) {
            FieldDto fieldDto = toFieldDto(field);
            fieldDtoList.add(fieldDto);
        }
        return fieldDtoList;
    }
}
