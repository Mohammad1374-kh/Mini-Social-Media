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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

/*---------------------------Return All posts of a page ---------------------------------------------------------------*/

    @GetMapping(value="/posts-list/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Post>> postsAll(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username)
                .orElseThrow(()->new NotFoundException("No User with the given username! : "+username));

        List<Post> postList = new ArrayList<Post>(user.getPosts());

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

/*---------------------------Publishing a new post by user's id ---------------------------------------------------------------*/

    @PutMapping(value = "/publish/{userId}" ,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> publishPostByUserId(@RequestBody PostDto postDto,@PathVariable Long userId){

            User user = userService.getUserById(userId)
                    .orElseThrow(()->new NotFoundException("No User with ID : "+userId));

            return new ResponseEntity<>(publishPost(user,postDto), HttpStatus.OK);
    }
/*---------------------------Publishing a new post by user's username ---------------------------------------------------------------*/

    @PutMapping(value = "/publish-by-username/{username}" ,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> publishPostByUsername(@RequestBody PostDto postDto,@PathVariable String username){

        User user = userService.getUserByUsername(username)
                .orElseThrow(()->new NotFoundException("No User with the given username! : "+username));

        return new ResponseEntity<>(publishPost(user,postDto), HttpStatus.OK);
    }

    public User publishPost(User user,PostDto postDto){
        //setting post's Creation date
        postDto.setCreatedDateDto(MyDate.getCurrentDate());
        Post newPost = postMapper.toPost(postDto);
        //setting post's content
        newPost.setContent(postDto.getContentDto());
        List<Post> posts = new ArrayList<>();
        posts.add(newPost);
        //setting post to the user
        user.setPosts(posts);

        return userService.createUser(user);
    }

}
