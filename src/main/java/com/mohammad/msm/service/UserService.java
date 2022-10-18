package com.mohammad.msm.service;

import com.mohammad.msm.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //saving User
    User createUser(User user);

    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();
    void UpdateUser(User user,Long userId);
    void deleteUserById(Long id);

    Page<User> getListPagination(int page, int size);
}
