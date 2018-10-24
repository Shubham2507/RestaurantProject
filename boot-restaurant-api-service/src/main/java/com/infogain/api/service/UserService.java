package com.infogain.api.service;

import java.util.List;

import com.infogain.api.dto.UserDto;
import com.infogain.api.entity.User;




public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(long id);
}
