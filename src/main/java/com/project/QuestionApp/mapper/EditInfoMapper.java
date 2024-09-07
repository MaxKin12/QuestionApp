package com.project.QuestionApp.mapper;

import com.project.QuestionApp.dto.EditInfoDto;
import com.project.QuestionApp.entity.User;
import org.springframework.stereotype.Component;

@Component
public class EditInfoMapper {
    public User toUser(EditInfoDto editInfoDto) {
        User user = new User();
        user.setEmail(editInfoDto.getEmail());
        user.setPassword(null);
        user.setFirstName(editInfoDto.getFirstName());
        user.setLastName(editInfoDto.getLastName());
        user.setPhoneNumber(editInfoDto.getPhoneNumber());

        return user;
    }
}
