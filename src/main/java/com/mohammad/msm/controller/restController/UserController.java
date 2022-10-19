package com.mohammad.msm.controller.restController;

import com.mohammad.msm.date.MyDate;
import com.mohammad.msm.dto.UserDto;
import com.mohammad.msm.exception.DuplicateContentException;
import com.mohammad.msm.exception.NotFoundException;
import com.mohammad.msm.mapper.UserMapper;
import com.mohammad.msm.model.User;
import com.mohammad.msm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    //TODO: Validations needed to be done
    //TODO: pagination needed to be done

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
/*

    //TODO: update method needs to be modified
    @PutMapping(value = "/update/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable Long userId ,@RequestBody UserDto userDto) {

        //check if there is any user is available with the given id
        User oldUser = userService.getUserById(userId)
                .orElseThrow(()->new NotFoundException("There is not any user available with the given id : "
                        + userId + " in database to be updated!"));

            String newUsername = userDto.getUsernameDto();

            //if the username not changed
            if(newUsername.equalsIgnoreCase(oldUser.getUsername())){
                //transferring properties from old object to new one
                userDto.setSignUpDateDto(oldUser.getSignUpDate());
                User newUser = userMapper.toUser(userDto);
                newUser.setId(userId);
                //delete the old user in data base
                userService.deleteUserById(userId);
                //save the new user object
                userService.createUser(newUser);
                return new ResponseEntity<>(newUser, HttpStatus.ACCEPTED);

            }
            else {//if the username changed
                Optional<User> userOptional=userService.getUserByUsername(newUsername);
                if(userOptional.isPresent()){//check if the username value conflicts with other usernames
                    throw new DuplicateContentException("There is already a user with this username : "
                            + userDto.getUsernameDto() + " in database");
                }

                else{//if the username changed but dose not have any conflict
                    //transferring properties from old object to new one
                    userDto.setSignUpDateDto(oldUser.getSignUpDate());
                    User newUser = userMapper.toUser(userDto);
                    newUser.setId(userId);
                    //delete the old user in data base
                    userService.deleteUserById(userId);
                    //save the new user object
                    userService.createUser(newUser);
                    return new ResponseEntity<>(newUser, HttpStatus.ACCEPTED);
                }

            }

            //TODO: followers , followings and posts should be set when they created

    }
*/


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {

        Optional<User> userOptional = userService.getUserById(id);
        if(userOptional.isPresent()) //if there was a user with the given id
        {
            userService.deleteUserById(id);
            return ResponseEntity.ok().body("The user with the given id :" + id + " has been deleted");
        }
        else{
            throw new NotFoundException("There is not any user available with the given id : "
                    + id + " in database");
        }
    }

    //TODO: Delete user by username method




}
