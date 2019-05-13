package com.springboot2.test1.dao;

import com.springboot2.test1.bean.Employee;

public interface EmployeeDao {

//	@Insert("insert into myemployeee(last_name) values (#{lastName})")
	int insert(Employee emp);
	
}
