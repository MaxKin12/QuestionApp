package com.project.QuestionApp.controller;


import com.project.QuestionApp.dto.ChangePasswordDto;
import com.project.QuestionApp.dto.EditInfoDto;
import com.project.QuestionApp.dto.UserDto;
import com.project.QuestionApp.entity.User;
import com.project.QuestionApp.mapper.EditInfoMapper;
import com.project.QuestionApp.mapper.UserMapper;
import com.project.QuestionApp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@AllArgsConstructor
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EditInfoMapper editInfoMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/user/edit_info/{id}")
    public UserDto editInfo(@RequestBody EditInfoDto editInfoDto, @PathVariable("id") Long userId) {
        User user = userService.findById(userId);
        if (user != null) {
            userService.editUserInfo(user, editInfoMapper.toUser(editInfoDto));
            return userMapper.toUserDto(user);
        } else {
            return null;
        }
    }

    @PostMapping("/user/change_pas/{id}")
    public UserDto changePassword(@RequestBody ChangePasswordDto changePasswordDto, @PathVariable("id") Long userId) {
        User user = userService.findById(userId);

        if (user != null && bCryptPasswordEncoder.matches(changePasswordDto.getPassword(), user.getPassword()) &&
                changePasswordDto.getNewPassword().equals(changePasswordDto.getConfNewPassword())) {
            userService.changeUserPassword(user, changePasswordDto.getNewPassword());

            return userMapper.toUserDto(user);
        } else {
            return null;
        }
    }

    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable("id") Long userId) {
        User user = userService.findById(userId);

        if (user != null) {
            return userMapper.toUserDto(user);
        } else {
            return null;
        }
    }

    @GetMapping("/user")
    public Long getUser() {
        User user = userService.getUserByLogin();

        return user.getId();
    }
}
