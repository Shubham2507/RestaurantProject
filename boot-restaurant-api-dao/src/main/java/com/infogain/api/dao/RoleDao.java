package com.infogain.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infogain.api.entity.Role;




@Repository
public interface RoleDao extends CrudRepository<Role, Long>{
	Role findRoleByName(String roleName);
}
