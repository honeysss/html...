package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.util.DBUtil;
import com.icss.vo.Department;

public class DepartmentDao {
	//查询所有部门
	public ArrayList<Department> selectAllDeptDAO() throws SQLException {
		ArrayList<Department> departmentList = new ArrayList<Department>();
		String sql = "select * from department order by deptId desc";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Department department = new Department();
			department.setDeptId(rs.getInt("deptId"));
			department.setDeptName(rs.getString("deptName"));
			departmentList.add(department);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return departmentList;
	}
	
	//添加部门
	public void addDeptDAO(String deptName) throws SQLException {
		String sql = "insert into department values(deptIdSeq.NEXTVAL, ?)";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, deptName);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//通过部门名称查询该部门是否已存在
	public boolean ifExistDeptDAO(String deptName) throws SQLException {
		boolean flag = false;
		String sql = "select * from department where deptName = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, deptName);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			flag = true;
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return flag;
	}
	
	//通过部门id删除部门
	public void deleteDepetByIdDAO(int deptId) throws SQLException {
		String sql = "delete from department where deptId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, deptId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//删除部门的同时把属于该员工的部门id设为-1
	public void deleteEmpFromDeptDAO(int deptId) throws SQLException {
		String sql = "update employee set empDeptId = -1 where empDeptId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, deptId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	public ArrayList<Department> SelectAllDepartmentsDAO() throws ClassNotFoundException, SQLException{
		ArrayList<Department> al =new ArrayList<Department>();
		DBUtil.getConnection();
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("select * from department");
		ResultSet rs =ps.executeQuery();
		Department dt =null; 
		while(rs.next()){
			dt=new Department(rs.getInt(1),rs.getString(2));
			al.add(dt);
			
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return al;
	}
	
}
