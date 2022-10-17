package com.mohammad.msm.repository;

import com.mohammad.msm.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findUserById(Long id);
    User findUserByUsername(String username);
    //List<User> findAllUser(Pageable pageable);

}
