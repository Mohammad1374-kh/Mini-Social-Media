package com.mohammad.msm.service;

import com.mohammad.msm.exception.NotFoundException;
import com.mohammad.msm.model.User;
import com.mohammad.msm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User createUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findUserById(id));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findUserByUsername(username));
    }

    @Override
    public List<User> getAllUsers() {
        Iterable<User> iterable = userRepository.findAll();
        ArrayList<User> listOfUsers = new ArrayList<>();
        for (User user: iterable) {
            listOfUsers.add(user);
        }
        return listOfUsers;
    }


    @Override
    public void updateUser(User user,Long id) {

        User editedUser = getUserById(id)
                .orElseThrow(() -> new NotFoundException("No User with this id" + id ));

        editedUser.setUsername(user.getUsername());
        editedUser.setFullName(user.getFullName());
        editedUser.setSignUpDate(user.getSignUpDate());
        //TODO: should new user's posts and followers be replaced too?
    }


    @Override
    public void deleteUserById(Long id) {
        getUserById(id)
                .orElseThrow(() -> new NotFoundException("No user with this id" + id));
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getListPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(
                Sort.Order.desc("id")));
        return userRepository.findAll(pageable);
    }
}
