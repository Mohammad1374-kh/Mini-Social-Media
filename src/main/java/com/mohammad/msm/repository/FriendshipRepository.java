package com.mohammad.msm.repository;

import com.mohammad.msm.model.Friendship;
import com.mohammad.msm.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends PagingAndSortingRepository<Friendship, Long> {

    boolean existsByFirstUserAndSecondUser(User first,User second);
    List<Friendship> findByFirstUser(User user);
    List<Friendship> findBySecondUser(User user);
}
