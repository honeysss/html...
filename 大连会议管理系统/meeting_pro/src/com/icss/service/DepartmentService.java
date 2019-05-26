package com.icss.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.dao.DepartmentDao;

import com.icss.vo.Department;


public class DepartmentService {
	
	DepartmentDao dd = new DepartmentDao();
	//查询所有部门
	public ArrayList<Department> selectAllDeptService() throws SQLException {
		return dd.selectAllDeptDAO();
	}
	
	//添加部门
	public void addDeptService(String deptName) throws SQLException {
		dd.addDeptDAO(deptName);
	}
	
	//通过部门名称查询该部门是否已存在
	public boolean ifExistDeptService(String deptName) throws SQLException {
		return dd.ifExistDeptDAO(deptName);
	}
	
	
	//通过部门id删除部门
	public void deleteDepetByIdService(int deptId) throws SQLException {
		dd.deleteDepetByIdDAO(deptId);
	}
	
	
	//删除部门的同时把属于该员工的部门id设为-1
	public void deleteEmpFromDeptService(int deptId) throws SQLException {
		dd.deleteEmpFromDeptDAO(deptId);
	}
	
	public ArrayList<Department> SelectAllDepartmentsDAO() throws ClassNotFoundException, SQLException{	
		return	dd.SelectAllDepartmentsDAO();
	}
}
