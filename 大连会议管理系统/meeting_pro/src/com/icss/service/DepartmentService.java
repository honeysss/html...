package com.icss.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.dao.DepartmentDao;

import com.icss.vo.Department;


public class DepartmentService {
	
	DepartmentDao dd = new DepartmentDao();
	//��ѯ���в���
	public ArrayList<Department> selectAllDeptService() throws SQLException {
		return dd.selectAllDeptDAO();
	}
	
	//��Ӳ���
	public void addDeptService(String deptName) throws SQLException {
		dd.addDeptDAO(deptName);
	}
	
	//ͨ���������Ʋ�ѯ�ò����Ƿ��Ѵ���
	public boolean ifExistDeptService(String deptName) throws SQLException {
		return dd.ifExistDeptDAO(deptName);
	}
	
	
	//ͨ������idɾ������
	public void deleteDepetByIdService(int deptId) throws SQLException {
		dd.deleteDepetByIdDAO(deptId);
	}
	
	
	//ɾ�����ŵ�ͬʱ�����ڸ�Ա���Ĳ���id��Ϊ-1
	public void deleteEmpFromDeptService(int deptId) throws SQLException {
		dd.deleteEmpFromDeptDAO(deptId);
	}
	
	public ArrayList<Department> SelectAllDepartmentsDAO() throws ClassNotFoundException, SQLException{	
		return	dd.SelectAllDepartmentsDAO();
	}
}
