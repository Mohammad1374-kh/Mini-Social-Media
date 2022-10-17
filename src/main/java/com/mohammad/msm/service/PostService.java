package com.mohammad.msm.service;

import com.mohammad.msm.model.Post;
import com.mohammad.msm.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {

    void savePost(Post post);
    Post getAPostOfAUserByPostIdAndUsername(Long postId,String username);
    Post getAPostOfAUserByPostIdAndUserId(Long postId,Long userId);

    //Equals to findAllByUserId in repo
    List<Post> getAllPostsOfAUserById(Long userId);

    //Equals to findAllByUserUsername in repo
    List<Post> getAllPostsOfAUserByUsername(String username);

    void UpdatePost(Post post);

    void deletePost(Long id);

    //equals to findAll in repo
    Page<Post> getListPagination(int page, int size);
}
