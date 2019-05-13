package com.springboot2.test2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot2.test2.bean.User;
import com.springboot2.test2.dao.UserDao;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	
	public Integer insert(User user2) {
		return userDao.insert(user2);
	}
}
