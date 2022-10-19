package com.mohammad.msm.service;

import com.mohammad.msm.dto.UserDto;
import com.mohammad.msm.model.Friendship;
import com.mohammad.msm.model.User;

import java.util.List;

public interface FriendshipService {

     void saveFriend(UserDto userDto1, Long id) throws NullPointerException;

     public List<User> getFriends(User user);
}
