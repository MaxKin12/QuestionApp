package com.project.QuestionApp.controller;

import com.project.QuestionApp.dto.LoginDto;
import com.project.QuestionApp.dto.SignUpDto;
import com.project.QuestionApp.dto.UserDto;
import com.project.QuestionApp.entity.User;
import com.project.QuestionApp.mapper.SignUpMapper;
import com.project.QuestionApp.mapper.UserMapper;
import com.project.QuestionApp.service.DefaultEmailService;
import com.project.QuestionApp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@AllArgsConstructor
@Slf4j
public class AuthentificationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SignUpMapper signUpMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private DefaultEmailService defaultEmailService;

    @PostMapping("/log_in")
    public UserDto logInUser(@RequestBody LoginDto loginDto) {
        User user = userService.findByEmail(loginDto.getEmail());

        if (user != null && bCryptPasswordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return userMapper.toUserDto(user);
        } else {
            return null;
        }
    }

    @PostMapping("/sign_up")
    public UserDto signUpUser(@RequestBody SignUpDto signupDto) {
        if (signupDto.getPassword().equals(signupDto.getConfPassword())) {
            User user = userService.registrateUser(signUpMapper.toUser(signupDto));
            try {
                defaultEmailService.sendSimpleEmail("vagugpf@mailto.plus", "sign_up",
                        signupDto.getFirstName() + " " + signupDto.getLastName() + ", you are successfully sign up"); // заменить на email юзера
            } catch (MailException mailException) {
                log.error("Error while sending out email..{}", mailException.getStackTrace());
            }
            return userMapper.toUserDto(user);
        } else {
            return null;
        }
    }
}
