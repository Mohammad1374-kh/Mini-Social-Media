package com.mohammad.msm.repository;

import com.mohammad.msm.model.Friendship;
import com.mohammad.msm.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends PagingAndSortingRepository<Friendship, Long> {

    List<Friendship> findAllByFollowRequestSenderId(Long id);
    List<Friendship> findAllByFollowRequestSenderUsername(String username);

    List<Friendship> findAllByFollowRequestReceiverId(Long id);
    List<Friendship> findAllByFollowRequestReceiverUsername(String username);

    Page<Friendship> findAll(Pageable pageable);
}
