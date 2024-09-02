package com.project.QuestionApp.mapper;

import com.project.QuestionApp.dto.OptionsDto;
import com.project.QuestionApp.entity.Options;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OptionsMapper {
    public List<OptionsDto> toOptionsDto(List<Options> optionsList) {
        List<OptionsDto> optionsDtoList = new ArrayList<>();
        for (Options options : optionsList) {
            OptionsDto optionsDto = new OptionsDto();
            optionsDto.setOption(options.getOption());
            optionsDtoList.add(optionsDto);
        }
        return optionsDtoList;
    }
}
