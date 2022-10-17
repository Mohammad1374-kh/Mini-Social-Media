package com.mohammad.msm.service;

import com.mohammad.msm.model.Post;
import com.mohammad.msm.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface PostService {

    void savePost(User user, String content);

    Optional<Post>  getAPostOfAUserByPostId(Long postId);

    //Equals to findAllByUserId in repo
    List<Post> getAllPostsOfAUserById(Long userId);

    //Equals to findAllByUserUsername in repo
    List<Post> getAllPostsOfAUserByUsername(String username);

    void UpdatePost(Post post,Long postId);

    void deletePost(Long postId);

    //equals to findAll in repo
    Page<Post> getListPagination(int page, int size);
}
