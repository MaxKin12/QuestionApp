package com.project.QuestionApp.mapper;

import com.project.QuestionApp.dto.AnswerDto;
import com.project.QuestionApp.dto.ResponseDto;
import com.project.QuestionApp.entity.Answer;
import com.project.QuestionApp.entity.Response;
import com.project.QuestionApp.repository.AnswerRepository;
import com.project.QuestionApp.repository.FieldRepository;
import com.project.QuestionApp.repository.ResponseRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public class ResponseMapper {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private AnswerMapper answerMapper;

    @Mapping(target = "answerId", source = "answerList.answerId")
    public Response toResponse(ResponseDto responseDto) {
        Response response = new Response();
        List<Answer> answerList = new ArrayList<>();
        for (AnswerDto answerDto : responseDto.getAnswerList()) {
            Answer answer = new Answer();
            answer.setAnswer(answerDto.getAnswer());
            answer.setResponse(responseRepository.save(response));
            answer.setField(fieldRepository.findById(answerDto.getFieldId()).orElse(null));
            answerRepository.save(answer);
            answerList.add(answer);
        }
        response.setAnswerList(answerList);
        responseRepository.save(response);

        return response;
    }

    @Mapping(target = "answerList", source = "response.answers")
    public ResponseDto toResponseDto(List<Response> responseList) {
        ResponseDto responseDto = new ResponseDto();
        List<AnswerDto> answerDtoList = new ArrayList<>();

        for (Response response : responseList) {
            answerDtoList.addAll(answerMapper.toAnswerDto(response.getAnswerList()));
        }
        System.out.println(answerDtoList);
        responseDto.setAnswerList(answerDtoList);

        return responseDto;
    }

    public ResponseDto toResponseDto(Response response) {
        ResponseDto responseDto = new ResponseDto();
        List<AnswerDto> answerDtoList = new ArrayList<>();
        for (Answer answer : response.getAnswerList()) {
            AnswerDto answerDto = new AnswerDto();
            answerDto.setFieldId(answer.getField().getId());
            answerDto.setAnswer(answer.getAnswer());
            answerDtoList.add(answerDto);
        }
        responseDto.setAnswerList(answerDtoList);

        return responseDto;
    }
}
