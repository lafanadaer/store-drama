package com.example.dramaserver.service;

import com.example.dramaserver.domain.User;

import java.util.List;

public interface UserService {

    boolean addUser(User user);

    boolean updateUserMsg(User user);

    boolean updateUserAvator(User user);

    boolean existUser(String username);

    boolean veritypasswd(String username, String password);

    boolean deleteUser(Integer id);

    List<User> allUser();

    List<User> userOfId(Integer id);

    List<User> loginStatus(String username);

}
