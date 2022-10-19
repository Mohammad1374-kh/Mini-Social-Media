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


/*-------------------------Reading users from data base based on id------------------------------------------------*/

    @GetMapping(value="/info/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> info(@PathVariable("id") Long id) {
        User user = userService.getUserById(id)
                .orElseThrow(()->new NotFoundException("No User with ID : "+id));
        return ResponseEntity.ok().body(user);
    }

/*-------------------------Reading users from data base based on username-----------------------------------------*/

    @GetMapping(value="/info-username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> infoUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username)
                .orElseThrow(()->new NotFoundException("No User with this username : "+username));
        return ResponseEntity.ok().body(user);
    }

/*-------------------------Reading all users from data base-------------------------------------------------------*/

    @GetMapping(value="/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> infoAll() {
       return userService.getAllUsers();
    }

/*---------------------------Creating a new users in DB---------------------------------------------------------------*/

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

/*---------------------------Updating an existed user from DB---------------------------------------------------------------*/

    @PutMapping(value = "/update/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable Long userId ,@RequestBody UserDto userDto) {

        User oldUser = userService.getUserById(userId)
                .orElseThrow(() -> new NotFoundException("There is not any user available with the given id : "
                        + userId + " in database to be updated!"));

        String newUsername = userDto.getUsernameDto();
        //if the username did not change
        if (newUsername.equalsIgnoreCase(oldUser.getUsername())) {
            User newUser = updateContent(oldUser, userDto, userId);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } else {//if the username changed

                Optional<User> userOptional = userService.getUserByUsername(newUsername);

                //checks if the username value conflicts with other usernames
                if (userOptional.isPresent()) {
                    throw new DuplicateContentException("There is already a user with this username : "
                            + userDto.getUsernameDto() + " in database");
                } else {//if the username changed but dose not have any conflict
                    User newUser = updateContent(oldUser, userDto, userId);
                    return new ResponseEntity<>(newUser, HttpStatus.OK);
                }

        }

    }
        public User updateContent(User user, UserDto userDto, Long userId){
            userDto.setSignUpDateDto(user.getSignUpDate());
            User newUser = userMapper.toUser(userDto);
            newUser.setId(userId);
            userService.createUser(newUser);
            return newUser;
        }

/*---------------------------Deleting a user from DB---------------------------------------------------------------*/
    //TODO: Delete user by username method

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




}
