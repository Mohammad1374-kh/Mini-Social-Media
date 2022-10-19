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

/*---------------------------Creating(Publish) a new post in DB---------------------------------------------------------------*/

    @PutMapping(value = "/publish/{userId}" ,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> publishPostByUserId(@RequestBody PostDto postDto,@PathVariable Long userId){

            User user = userService.getUserById(userId)
                    .orElseThrow(()->new NotFoundException("No User with ID : "+userId));
            //setting post's Creation date
            postDto.setCreatedDateDto(MyDate.getCurrentDate());
            Post newPost = postMapper.toPost(postDto);
            //setting post's content
            newPost.setContent(postDto.getContentDto());
            List<Post> posts = new ArrayList<>(1);
            posts.add(newPost);
            //setting post to the user
            user.setPosts(posts);

            return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);

    }

}
