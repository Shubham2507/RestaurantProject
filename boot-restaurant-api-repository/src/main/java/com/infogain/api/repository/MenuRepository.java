package com.infogain.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infogain.api.entity.Menu;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
