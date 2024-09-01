package com.project.QuestionApp.service;

import com.project.QuestionApp.entity.Response;
import com.project.QuestionApp.repository.ResponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResponseService {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private AnswerService answerService;

    public Response addResponse(Response response) {
        return responseRepository.save(response);
    }

    public Response findResponse(Long id) {
        return responseRepository.findById(id).orElse(null);
    }

    public List<Response> findAllResponses() {
        return responseRepository.findAll();
    }
}
