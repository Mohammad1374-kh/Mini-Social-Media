package com.mohammad.msm.controller.restController;

import com.mohammad.msm.dto.UserDto;
import com.mohammad.msm.mapper.UserMapper;
import com.mohammad.msm.model.User;
import com.mohammad.msm.repository.UserRepository;
import com.mohammad.msm.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping(value = "/add-user")
    public ResponseEntity<User> addUser( @RequestBody UserDto userDto){

        User user = userMapper.toUser(userDto);
        User addedUser = userService.createUser(user);
        //logger.info("User has been added");
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }
}
