package com.springboot2.test1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot2.test1.bean.Employee;
import com.springboot2.test1.dao.EmployeeDao;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDao employeeDao;
	
	public Integer insert(Employee emp) {
		return employeeDao.insert(emp);
	}
}
