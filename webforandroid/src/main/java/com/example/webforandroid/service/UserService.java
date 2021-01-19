package com.example.webforandroid.service;

import com.example.webforandroid.mapper.UserMapper;
import com.example.webforandroid.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    //@Autowired
    UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }


    public int addUser(User user){
        return userMapper.addUser(user);
    }

    public User getUserByPhone(String phone){
        return userMapper.getUserByPhone(phone);
    }

    public List<User> getAllUsers(){
        if(userMapper == null){
            System.out.println("NULL");
        }
        return userMapper.getAllUsers();
    }
    
}
