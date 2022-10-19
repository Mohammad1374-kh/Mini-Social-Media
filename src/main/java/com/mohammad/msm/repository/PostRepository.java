package com.mohammad.msm.repository;

import com.mohammad.msm.model.Post;
import com.mohammad.msm.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    /*List<Post> findAllByUserId(Long id);
    List<Post> findAllByUserUsername(String username);*/
    Page<Post> findAll(Pageable pageable);
    Post findPostById(Long postId);




}
