package com.springboot2.test1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot2.test1.bean.Employee;
import com.springboot2.test1.dao.EmployeeDao;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDao employeeDao;
	/**
	 * 添加事务管理
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	@Transactional(transactionManager="test1TransactionManager",rollbackFor=Exception.class)
	public Integer insert(Employee emp) throws Exception{
		int i=employeeDao.insert(emp);
//		i=i/0;
		/**
		 * 抛出异常事务无效：默认检测unchecked异常才回滚，checked异常也回滚需要设置rollbackFor=Exception.class
		 * https://www.cnblogs.com/syp172654682/p/9811341.html
		 */
		if("test".equals(emp.getLastName())) {
			throw new Exception("异常");
		}
		return i;
	}
}
