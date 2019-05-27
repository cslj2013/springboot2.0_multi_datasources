package com.springboot2.test1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot2.test1.bean.Employee;
import com.springboot2.test1.dao.EmployeeDao;
import com.springboot2.test2.bean.User;
import com.springboot2.test2.service.UserService;
import com.sun.media.jfxmedia.logging.Logger;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	UserService userService;
	/**
	 * 添加事务管理
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	@Transactional(transactionManager="test1TransactionManager",rollbackFor=Exception.class)
	public Integer insert(Employee emp) throws Exception{
//		i=i/0;
		/**
		 * 抛出异常事务无效：默认检测unchecked异常才回滚，checked异常也回滚需要设置rollbackFor=Exception.class
		 * https://www.cnblogs.com/syp172654682/p/9811341.html
		 */
//		if("test".equals(emp.getLastName())) {
//			throw new Exception("异常");
//		}
		int i=this.other(emp);
		return i;
	}
	
	@Transactional(transactionManager="test1TransactionManager")
	public Integer other(Employee emp) throws Exception{
		int i=employeeDao.insert(emp);
		/*try {
			i=i/0;
		}catch(Exception e) {
			Logger.logMsg(Logger.INFO, "异常");
			throw new Exception();
		}*/
		return i;
	}
	
	@Transactional(transactionManager="test1TransactionManager")
	public Integer insertEmployeeAndUser(String name,Integer age) throws Exception{
		//第一个数据源
		Employee emp=new Employee();
		emp.setLastName(name);
		int insert = employeeDao.insert(emp);
		//第二个数据源
		User user = new User();
		user.setAge(age);
		user.setName(name);
		Integer insert2 = userService.insert(user);
		//制造unchecked异常
		int j=insert/0;
		return insert2+insert;
		//结果：employeeDao.insert回滚，userService.insert插入成功；多事务问题
	}
}
