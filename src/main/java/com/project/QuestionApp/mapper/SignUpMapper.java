package com.project.QuestionApp.mapper;

import com.project.QuestionApp.dto.SignUpDto;
import com.project.QuestionApp.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SignUpMapper {
    public User toUser(SignUpDto signupDto) {
        User user = new User();
        user.setEmail(signupDto.getEmail());
        user.setPassword(signupDto.getPassword());
        user.setFirstName(signupDto.getFirstName());
        user.setLastName(signupDto.getLastName());
        user.setPhoneNumber(signupDto.getPhoneNumber());

        return user;
    }
}