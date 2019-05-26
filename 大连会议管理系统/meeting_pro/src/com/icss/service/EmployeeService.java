package com.icss.service;

import java.sql.SQLException;
import java.util.ArrayList;
import com.icss.dao.EmployeeDao;
import com.icss.vo.Employee;

public class EmployeeService {
	EmployeeDao ed = new EmployeeDao();

	//通过用户名查询是否存在该用户
	public boolean selectByNameService(String username) throws SQLException {
		boolean flag = false;
		if (ed.selectByNameDAO(username) != null) {
			flag = true;
		}
		return flag;
	}
		
	//通过用户名和密码查询该用户
	public Employee selectByNameAndPwdService(String username, String pwd) throws SQLException {
		return ed.selectByNameAndPwdDAO(username, pwd);
	}
	
	//注册用户
	public void userRegisterService(String empName, String empTel, String empEmail,  int empDeptId, String username, String userPwd) throws SQLException {
		ed.userRegisterDAO(empName, empTel, empEmail, empDeptId, username, userPwd);
	}
	
	//通过用户id修改用户密码
	public void updatePwdService(int empId, String pwd) throws SQLException {
		ed.updatePwdDAO(empId, pwd);
	}
	
	
	//查询所有用户
	public ArrayList<Employee> selectAllUserService() throws SQLException {
		return ed.selectAllUserDAO();
	}
	
	//查询所有正在审核的用户
	public ArrayList<Employee> selectAllUnpassedUserService() throws SQLException {
		return ed.selectAllUnpassedUserDAO();
	}
	
	//通过员工id找到员工
	public Employee selectUserByIdService(int empId) throws SQLException {
		return ed.selectUserByIdDAO(empId);
	}
	
	//管理员修改员工信息
	public void updateEmpService(Employee emp) throws SQLException {
		ed.updateEmpDAO(emp);
	}
	
	//通过id删除用户
	public void deleteEmpByIdService(int empId) throws SQLException {
		ed.deleteEmpByIdDAO(empId);
	}
	
	//管理员添加用户
	public void adminAddEmployeeService(Employee emp) throws SQLException {
		ed.adminAddEmployeeDAO(emp);
	}
	
	//通过员工id将会议和信息表中的信息删除
	public void deleteMeetingAndEmployeeByEmpIdService(int empId) throws SQLException {
		ed.deleteMeetingAndEmployeeByEmpIdDAO(empId);
	}
	
	
	//查询一共有几页数据
	public int employeesPageService(int showNum) throws SQLException {
		int count = ed.employeesCountDAO();
		int allPageNum = count % showNum == 0 ? count/showNum : count/showNum + 1;
		return allPageNum;
	}
	
	//查询一共有多少员工
	public int employeesCountService() throws SQLException {
		return ed.employeesCountDAO();
	}
	
	//分页查询员工数据
	public ArrayList<Employee> selectEmployeesByPageService(int pageNum, int showNum) throws SQLException {
		return ed.selectEmployeesByPageDAO(pageNum, showNum);
	}
	
	//通过员工id设置员工账号不通过审核 2
	public void notApproveByIdService(int empId) throws SQLException {
		ed.notApproveByIdDAO(empId);
	}
	
	//通过员工id设置员工账号通过审核 1
	public void approveByIdService(int empId) throws SQLException {
		ed.approveByIdDAO(empId);
	}
	
	//通过部门id找到员工
	public ArrayList<Employee> selectUserByDeptIdService(int deptId) throws SQLException {
		return ed.selectUserByDeptIdDAO(deptId);
	}
	
	
	//员工自己修改自己的信息
	public void updateEmpByUserService(Employee emp) throws SQLException {
		ed.updateEmpByUserDAO(emp);
	}
	
	
	//通过员工姓名 用户名 状态查询所有员工
	public ArrayList<Employee> selectEmpByRulesService(String empName, String username, int userStatus) throws SQLException {
		return ed.selectEmpByRulesDAO(empName, username, userStatus);
	}
	
	//通过员工姓名 用户名 状态查询所有员工 分页
	public ArrayList<Employee> selectEmpByPageService(String empName, String username, int userStatus, int pageNum, int showNum) throws SQLException {
		return ed.selectEmpByPageDAO(empName, username, userStatus, pageNum, showNum);
	}
	public ArrayList<Employee> SelectEmployeesOfDeptDAO(int deptId) throws ClassNotFoundException, SQLException{
		 return ed.SelectEmployeesOfDeptDAO(deptId);
	 }
	 public int SelectIdByNameService(String username) throws ClassNotFoundException, SQLException{	
		 return ed.SelectIdByNameDAO(username);
		 
	 }
	
}
