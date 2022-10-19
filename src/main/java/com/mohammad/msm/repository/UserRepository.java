package com.mohammad.msm.repository;

import com.mohammad.msm.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findUserById(Long id);
    User findUserByUsername(String username);
    Page<User> findAll(Pageable pageable);



}
