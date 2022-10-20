package com.mohammad.msm.service;

import com.mohammad.msm.dto.UserDto;
import com.mohammad.msm.model.Friendship;
import com.mohammad.msm.model.User;

import java.util.List;
import java.util.Optional;

public interface FriendshipService {

     void saveFriend(UserDto userDto1, Long id) throws NullPointerException;

     List<User> getFriends(User user);

     void deleteById(Long Id);

      Optional<Friendship> getFriendshipById(Long friendshipId);
}
