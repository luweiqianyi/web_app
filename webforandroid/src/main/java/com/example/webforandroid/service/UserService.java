package com.example.webforandroid.service;

import com.example.webforandroid.mapper.UserMapper;
import com.example.webforandroid.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class UserService {
    //@Autowired
    UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    /**
     * 增加新用户
     * @param user
     * @return
     * @throws SQLIntegrityConstraintViolationException
     */
    public int addUser(User user) throws SQLIntegrityConstraintViolationException {
        return userMapper.addUser(user);
    }

    /**
     * 根据电话号码查询用户
     * @param phone
     * @return 查到，正常返回；没查到，返回null
     */
    public User getUserByPhone(String phone){
        return userMapper.getUserByPhone(phone);
    }

    /**
     * 得到所有用户
     * @return
     */
    public List<User> getAllUsers(){
        if(userMapper == null){
            System.out.println("NULL");
        }
        return userMapper.getAllUsers();
    }
    
}
