package com.mohammad.msm.service;

import com.mohammad.msm.date.MyDate;
import com.mohammad.msm.dto.UserDto;
import com.mohammad.msm.exception.NotFoundException;
import com.mohammad.msm.mapper.UserMapper;
import com.mohammad.msm.model.Friendship;
import com.mohammad.msm.model.User;
import com.mohammad.msm.repository.FriendshipRepository;
import com.mohammad.msm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FriendshipServiceImpl implements FriendshipService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    FriendshipRepository friendshipRepository;

    @Override
    public void saveFriend(UserDto userDto1, Long id) throws NullPointerException {
        User user = userRepository.findUserById(id);
        UserDto userDto2 = userMapper.toUserDto(user);
        Friendship friend = new Friendship();
        User user1 = userRepository.findUserByUsername(userDto1.getUsernameDto());
        User user2 = userRepository.findUserByUsername(userDto2.getUsernameDto());
        User firstUser = user1;
        User secondUser = user2;
        if (user1.getId().compareTo(user2.getId()) > 0 ) {
            firstUser = user2;
            secondUser = user1;
        }
        if( !(friendshipRepository.existsByFirstUserAndSecondUser( firstUser,secondUser)) ){
            friend.setCreatedDate(MyDate.getCurrentDate());
            friend.setFirstUser(firstUser);
            friend.setSecondUser(secondUser);
             friendshipRepository.save(friend);
        }

    }

    public List<User> getFriends(User currentUser){
       /* UserDto currentUserDto = securityService.getUser();
        User currentUser = userRepository.findUserById(userMapper.toUser(currentUserDto).getId());*/
        List<Friendship> friendsByFirstUser = friendshipRepository.findByFirstUser(currentUser);
        List<Friendship> friendsBySecondUser = friendshipRepository.findBySecondUser(currentUser);
        List<User> friendUsers = new ArrayList<>();

        for (Friendship friend : friendsByFirstUser) {
            friendUsers.add(userRepository.findUserById(friend.getSecondUser().getId()));
        }
        for (Friendship friend : friendsBySecondUser) {
            friendUsers.add(userRepository.findUserById(friend.getFirstUser().getId()));
        }
        return friendUsers;
    }
}