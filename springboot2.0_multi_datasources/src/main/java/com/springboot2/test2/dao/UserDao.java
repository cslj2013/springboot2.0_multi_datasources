package com.springboot2.test2.dao;

import com.springboot2.test2.bean.User;

public interface UserDao {

//		@Insert("insert into myuser(name,age) values (#{name},#{age})")
		int insert(User user);
	
}
