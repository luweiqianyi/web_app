package com.example.webforandroid.mapper;

import com.example.webforandroid.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
   int addUser(User user);
   User getUserByPhone(String phone);
   List<User> getAllUsers();
}
