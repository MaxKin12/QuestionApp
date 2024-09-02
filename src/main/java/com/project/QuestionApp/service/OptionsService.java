package com.project.QuestionApp.service;

import com.project.QuestionApp.entity.Options;
import com.project.QuestionApp.repository.OptionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OptionsService {
    @Autowired
    private OptionsRepository optionsRepository;

    public Options save(Options options) {
        return optionsRepository.save(options);
    }

    public void update(List<Options> options, List<Options> updatedOptions) {
        for (int i = 0; i < updatedOptions.size(); i++) {
            options.set(i, updatedOptions.get(i));
            if (options.size() >= i) {
                options.add(updatedOptions.get(i));
            }
        }
    }
}
