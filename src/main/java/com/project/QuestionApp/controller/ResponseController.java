package com.project.QuestionApp.controller;

import com.project.QuestionApp.dto.ResponseDto;
import com.project.QuestionApp.entity.Response;
import com.project.QuestionApp.mapper.ResponseMapper;
import com.project.QuestionApp.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@AllArgsConstructor
public class ResponseController {
    @Autowired
    private ResponseService responseService;
    @Autowired
    private ResponseMapper responseMapper;

    @PostMapping("/questionnaires/add")
    public ResponseDto addResponse(@RequestBody ResponseDto responseDto) {
        Response response = responseService.addResponse(responseMapper.toResponse(responseDto));
        return responseMapper.toResponseDto(response);
    }

    @GetMapping("/questionnaires/get")
    public ResponseDto getAllResponse() {
        return responseMapper.toResponseDto(responseService.findAllResponses());
    }

    @GetMapping("/questionnaires/get/{id}")
    public ResponseDto getAllResponse(@PathVariable("id") Long responseId) {
        return responseMapper.toResponseDto(responseService.findResponse(responseId));
    }

    @PutMapping("/questionnaires/update/{id}")
    public ResponseDto updateField(@RequestBody ResponseDto responseDto, @PathVariable("id") Long responseId) {
        // TODO
        return null;
    }

    @DeleteMapping("/questionnaires/delete/{id}")
    public void deleteResponse(@PathVariable("id") Long responseId) {
        // TODO
    }
}
