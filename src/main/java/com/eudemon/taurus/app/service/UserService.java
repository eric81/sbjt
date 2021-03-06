package com.eudemon.taurus.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.eudemon.taurus.app.dao.UserDao;
import com.eudemon.taurus.app.entities.User;

@Component
public class UserService {
	@Autowired
	private UserDao userDao;

	public User getUser(long id) {
		User user = userDao.findOne(id);
		return user;
	}

	public User getUser(String name) {
		User user = userDao.findByName(name);
		return user;
	}

	public Page<User> getUserList(Pageable pageable) {
		Page<User> rs = userDao.findAll(pageable);
		return rs;
	}
	
	public Page<User> getUserListByRoles(String roles, Pageable pageable) {
		Page<User> rs = userDao.findByRoles(roles, pageable);
		return rs;
	}

	public void addUser(User user) {
		User us = userDao.saveAndFlush(user);
		user.setId(us.getId());
	}
	
	public void modify(User user) {
		userDao.saveAndFlush(user);
	}

	public void delete(long id) {
		userDao.delete(id);
	}
	
	public void modify(long id, String role, String permissions) {
		User user = userDao.findOne(id);
		user.setRoles(role);
		user.setPermissions(permissions);
		userDao.saveAndFlush(user);
	}
}
