package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.util.DBUtil;
import com.icss.vo.Department;

public class DepartmentDao {
	//��ѯ���в���
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
	
	//��Ӳ���
	public void addDeptDAO(String deptName) throws SQLException {
		String sql = "insert into department values(deptIdSeq.NEXTVAL, ?)";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, deptName);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//ͨ���������Ʋ�ѯ�ò����Ƿ��Ѵ���
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
	
	//ͨ������idɾ������
	public void deleteDepetByIdDAO(int deptId) throws SQLException {
		String sql = "delete from department where deptId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, deptId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//ɾ�����ŵ�ͬʱ�����ڸ�Ա���Ĳ���id��Ϊ-1
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
