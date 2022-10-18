package com.mohammad.msm.controller.restController;

import com.mohammad.msm.date.MyDate;
import com.mohammad.msm.dto.UserDto;
import com.mohammad.msm.mapper.UserMapper;
import com.mohammad.msm.model.User;
import com.mohammad.msm.repository.UserRepository;
import com.mohammad.msm.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @PostMapping(value = "/add-user" ,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser( @RequestBody UserDto userDto){
//TODO: Adding logic for rejecting repetitive Usernames

        userDto.setSignUpDateDto(MyDate.getCurrentDate());
        User user = userMapper.toUser(userDto);
        User addedUser = userService.createUser(user);
        //logger.info("User has been added");
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }
}
