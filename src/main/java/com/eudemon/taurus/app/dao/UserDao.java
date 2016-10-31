package com.eudemon.taurus.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eudemon.taurus.app.entities.User;

public interface UserDao extends JpaRepository<User, Long> {
	User findByName(String name);
	
	Page<User> findByRoles(String roles, Pageable pageable);
}
