package com.example.dramaserver.service.impl;

import com.example.dramaserver.dao.UserMapper;
import com.example.dramaserver.domain.User;
import com.example.dramaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(User user) {
        return userMapper.insertSelective(user) >0 ?true:false;
    }

    @Override
    public boolean updateUserMsg(User user) {
        return userMapper.updateUserMsg(user) >0 ?true:false;
    }

    @Override
    public boolean updateUserAvator(User user) {

        return userMapper.updateUserAvator(user) >0 ?true:false;
    }

    @Override
    public boolean existUser(String username) {
        return userMapper.existUsername(username)>0 ? true:false;
    }

    @Override
    public boolean veritypasswd(String username, String password) {

        return userMapper.verifyPassword(username, password)>0?true:false;
    }

    //    删除用户
    @Override
    public boolean deleteUser(Integer id) {
        return userMapper.deleteUser(id) >0 ?true:false;
    }

    @Override
    public List<User> allUser() {
        return userMapper.allUser();
    }

    @Override
    public List<User> userOfId(Integer id) {

        return userMapper.userOfId(id);
    }

    @Override
    public List<User> loginStatus(String username) {

        return userMapper.loginStatus(username);
    }
}
