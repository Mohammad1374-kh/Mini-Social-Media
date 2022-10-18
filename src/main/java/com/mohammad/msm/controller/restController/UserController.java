package com.mohammad.msm.controller.restController;

import com.mohammad.msm.date.MyDate;
import com.mohammad.msm.dto.UserDto;
import com.mohammad.msm.exception.DuplicateContentException;
import com.mohammad.msm.exception.NotFoundException;
import com.mohammad.msm.mapper.UserMapper;
import com.mohammad.msm.model.User;
import com.mohammad.msm.repository.UserRepository;
import com.mohammad.msm.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;


    //info : reading users from data base based on id
    @GetMapping(value="/info/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> info(@PathVariable("id") Long id) {
        User user = userService.getUserById(id)
                .orElseThrow(()->new NotFoundException("No User with ID : "+id));
        return ResponseEntity.ok().body(user);
    }

    //info : reading users from data base based on username
    @GetMapping(value="/info-username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> infoUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username)
                .orElseThrow(()->new NotFoundException("No User with this username : "+username));
        return ResponseEntity.ok().body(user);
    }


    //infoAll : reading all users from data base
    @GetMapping(value="/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> infoAll() {
       return userService.getAllUsers();
    }


    @PostMapping(value = "/add-user" ,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser( @RequestBody UserDto userDto){

        Optional<User> userOptional=userService.getUserByUsername(userDto.getUsernameDto());
        if(!userOptional.isPresent()) //if there was not a user with the same Username
        {
            userDto.setSignUpDateDto(MyDate.getCurrentDate());
            User user = userMapper.toUser(userDto);
            User addedUser = userService.createUser(user);
            //logger.info("User has been added");
            return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
        }
        else {
           throw new DuplicateContentException("There is already a user with this username : "
                   + userDto.getUsernameDto() + " in database");
        }
    }


}
