package com.infogain.api.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infogain.api.entity.Menu;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {


Optional<Menu> findByItemName(String mname);
}
