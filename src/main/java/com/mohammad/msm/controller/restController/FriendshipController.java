package com.mohammad.msm.controller.restController;

import com.mohammad.msm.dto.UserDto;
import com.mohammad.msm.exception.NotFoundException;
import com.mohammad.msm.mapper.UserMapper;
import com.mohammad.msm.model.Friendship;
import com.mohammad.msm.model.User;
import com.mohammad.msm.service.FriendshipService;
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
@RequestMapping("/api/friendship")
@AllArgsConstructor
public class FriendshipController {

    @Autowired
    FriendshipService friendshipService;
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
/*----------------------------------Making to pages follow each other--------------------------------------------------------*/

    @PostMapping(value = "/follow-friend/{friendId}" ,
            consumes = MediaType.APPLICATION_JSON_VALUE
            /*produces = MediaType.APPLICATION_JSON_VALUE*/)
    public ResponseEntity<String> followUser(@RequestBody UserDto userDto, @PathVariable Long friendId ){
        User user = userService.getUserById(friendId)
                .orElseThrow(()->new NotFoundException("No User with ID : "+ friendId));
        friendshipService.saveFriend(userDto,friendId);
        String message = "Pages "+user.getUsername()+" and "+ userDto.getUsernameDto() +" followed each other" ;
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
/*----------------------------------Making to pages unfollow each other--------------------------------------------------------*/
//TODO: logic needs to be modified
    @DeleteMapping("/unfollow-friends/{friendshipId}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long friendshipId ) {

        Friendship friendship = friendshipService.getFriendshipById(friendshipId)
                .orElseThrow(()->new NotFoundException("No User with ID : "+ friendshipId));

        friendshipService.deleteById(friendshipId);
        return new ResponseEntity<>("Pages unfollowed!", HttpStatus.OK);
    }
/*----------------------------------list of friendships--------------------------------------------------------*/

    @GetMapping(value = "/listFriends/{username}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getFriends(@PathVariable String username) {
        User user = userService.getUserByUsername(username)
                .orElseThrow(()->new NotFoundException("No User with this username : "+username));
        List<User> myFriends = friendshipService.getFriends(user);
        return new ResponseEntity<>(myFriends, HttpStatus.OK);
    }
}
