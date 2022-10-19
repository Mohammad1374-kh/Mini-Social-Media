package com.mohammad.msm.service;

import com.mohammad.msm.exception.NotFoundException;
import com.mohammad.msm.model.Post;
import com.mohammad.msm.model.User;
import com.mohammad.msm.repository.PostRepository;
import com.mohammad.msm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public void savePost(User user, String content) {
        Post post = new Post();
        post.setContent(content);
        postRepository.save(post);
    }


    @Override
    public Optional<Post> getAPostOfAUserByPostId(Long postId) {

      return Optional.ofNullable(postRepository.findPostById(postId));

    }



    @Override
    public void UpdatePost(Post post, Long oldPostId) {

        Post editedPost = getAPostOfAUserByPostId(oldPostId)
                .orElseThrow(() -> new NotFoundException("No Post with this id" + oldPostId ));

        editedPost.setContent(post.getContent());
        editedPost.setCreatedDate(post.getCreatedDate());

    }


    @Override
    public void deletePost(Long postId) {

        getAPostOfAUserByPostId(postId)
                .orElseThrow(() -> new NotFoundException("No Post with this id" + postId ));

        postRepository.deleteById(postId);

    }


    @Override
    public Page<Post> getListPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(
                Sort.Order.desc("id")));
        return postRepository.findAll(pageable);
    }
}
