package com.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot2.test1.bean.Employee;
import com.springboot2.test1.service.EmployeeService;
import com.springboot2.test2.bean.User;
import com.springboot2.test2.service.UserService;

@RestController
public class MultiDataSourceController {
	@Autowired
	EmployeeService employeeService1;
	@Autowired
	UserService userService;
	
	@RequestMapping("/insertEmployee")
	public String insert(String lastName) throws Exception{
		Employee emp=new Employee();
		emp.setLastName(lastName);
		int i=employeeService1.insert(emp);
		return i+"";
	}
	
	//接收json格式请求
	@RequestMapping("/insertUser")
	public String insert(@RequestBody User user){
		User user2=new User();
		user2.setAge(user.getAge());
		user2.setName(user.getName());
		int i=userService.insert(user2);
		return i+"";
	}
	//接收普通格式多参数请求
	@RequestMapping("/insertUser2")
	public String insertUser2(@RequestParam("name") String name,@RequestParam("age") Integer age){
		User user2=new User();
		user2.setAge(age);
		user2.setName(name);
		int i=userService.insert(user2);
		return i+"";
	}
	
	@RequestMapping("/insertEmployeeAndUser")
	public String insertEmployeeAndUser(@RequestParam("name") String name,@RequestParam("age") Integer age) throws Exception{
		int i=employeeService1.insertEmployeeAndUser(name,age);
		return i+"";
	}
}