package com.project.QuestionApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpDto {
    private String email;
    private String password;
    private String confPassword;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}

