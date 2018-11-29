package com.infogain.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.infogain.api.entity.User;




@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Long> {
	
    User findByUsername(String username);
    //User deleteByUsername(String username);
    
}
