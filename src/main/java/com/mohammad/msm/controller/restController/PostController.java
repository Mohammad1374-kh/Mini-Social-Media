package com.mohammad.msm.controller.restController;

import com.mohammad.msm.date.MyDate;
import com.mohammad.msm.dto.PostDto;
import com.mohammad.msm.dto.UserDto;
import com.mohammad.msm.exception.DuplicateContentException;
import com.mohammad.msm.exception.NotFoundException;
import com.mohammad.msm.mapper.PostMapper;
import com.mohammad.msm.mapper.UserMapper;
import com.mohammad.msm.model.Post;
import com.mohammad.msm.model.User;
import com.mohammad.msm.service.PostService;
import com.mohammad.msm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {

    @Autowired
    UserService userService;
    @Autowired
    PostMapper postMapper;
    @Autowired
    PostService postService;

/*---------------------------Return All posts of a page by page's username sorted---------------------------------------------------------------*/

    @GetMapping(value="/posts-list/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Set<Post>> showAllPostsByUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username)
                .orElseThrow(()->new NotFoundException("No User with the given username! : "+username));

        Set<Post> postList = new HashSet<>(user.getPosts());

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }
/*---------------------------Return All posts of a page by page's user Id sorted---------------------------------------------------------------*/

    @GetMapping(value="/posts-list-Id/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Set<Post>> showAllPostsById(@PathVariable Long userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(()->new NotFoundException("No User with the given username! : "+ userId));

        Set<Post> postList = new HashSet<>(user.getPosts());

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

/*---------------------------Publishing a new post by user's id ---------------------------------------------------------------*/

    @PostMapping(value = "/publish/{userId}" ,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> publishPostByUserId(@RequestBody PostDto postDto,@PathVariable Long userId){

        User user = userService.getUserById(userId)
                .orElseThrow(()->new NotFoundException("No User with ID : "+userId));


        return new ResponseEntity<>(publishPost(user,postDto), HttpStatus.OK);
    }

/*---------------------------Publishing a new post by user's username ---------------------------------------------------------------*/

    @PostMapping(value = "/publish-by-username/{username}" ,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> publishPostByUsername(@RequestBody PostDto postDto,@PathVariable String username){

        User user = userService.getUserByUsername(username)
                .orElseThrow(()->new NotFoundException("No User with the given username! : "+username));

        return new ResponseEntity<>(publishPost(user,postDto), HttpStatus.OK);
    }

    public Post publishPost(User user,PostDto postDto){
        postDto.setCreatedDateDto(MyDate.getCurrentDate());
        Set<Post> Posts= user.getPosts();
        Posts.add(postMapper.toPost(postDto));

        return postService.savePost(postDto);
    }

}
