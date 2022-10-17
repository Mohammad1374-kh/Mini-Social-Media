package com.mohammad.msm.service;

import com.mohammad.msm.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    void createUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    void UpdateUser(User user);
    void deleteUser(Long id);

    Page<User> getListPagination(int page, int size);
}
