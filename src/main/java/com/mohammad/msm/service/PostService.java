package com.mohammad.msm.service;

import com.mohammad.msm.dto.PostDto;
import com.mohammad.msm.model.Post;
import com.mohammad.msm.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface PostService {

    Post savePost(PostDto postDto);

    Optional<Post>  getAPostOfAUserByPostId(Long postId);


    void UpdatePost(Post post,Long postId);

    void deletePost(Long postId);

    Page<Post> getListPagination(int page, int size);
}
