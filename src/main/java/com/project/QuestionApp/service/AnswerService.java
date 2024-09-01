package com.project.QuestionApp.service;

import com.project.QuestionApp.entity.Answer;
import com.project.QuestionApp.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@AllArgsConstructor
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public Answer addAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Long getAnswerById(Long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new NotFoundException("Answer not found with ID: " + answerId));
        return answer.getId(); // Assuming the ID is of type Long
    }
}