package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.util.DBUtil;
import com.icss.vo.Employee;


public class EmployeeDao {
	
	//通过用户名查询用户
	//是否存在该用户
	public Employee selectByNameDAO(String username) throws SQLException {
		Employee employee = null;
		String sql = "select * from employee where username = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			employee = new Employee();
			employee.setEmpId(rs.getInt("empId"));
			employee.setEmpName(rs.getString("empName"));
			employee.setEmpTel(rs.getString("empTel"));
			employee.setEmpEmail(rs.getString("empEmail"));
			employee.setEmpDeptId(rs.getInt("empDeptId"));
			employee.setUsername(rs.getString("username"));
			employee.setUserPwd(rs.getString("userPwd"));
			employee.setUserStatus(rs.getInt("userStatus"));
			employee.setUserRole(rs.getInt("userRole"));
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return employee;
	}
	
	
	//通过用户名和密码判断是否存在该用户
	public Employee selectByNameAndPwdDAO(String username, String pwd) throws SQLException {
		Employee employee = null;
		String sql = "select * from employee where username = ? and userPwd = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, pwd);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			employee = new Employee();
			employee.setEmpId(rs.getInt("empId"));
			employee.setEmpName(rs.getString("empName"));
			employee.setEmpTel(rs.getString("empTel"));
			employee.setEmpEmail(rs.getString("empEmail"));
			employee.setEmpDeptId(rs.getInt("empDeptId"));
			employee.setUsername(rs.getString("username"));
			employee.setUserPwd(rs.getString("userPwd"));
			employee.setUserStatus(rs.getInt("userStatus"));
			employee.setUserRole(rs.getInt("userRole"));
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return employee;
	}
	
	
// 注册用户
	public void userRegisterDAO(String empName, String empTel, String empEmail,  int empDeptId, String username, String userPwd) throws SQLException {
		String sql = "INSERT INTO employee(empId, empName, empTel, empEmail, empDeptId, username, userPwd) VALUES (empIdSeq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, empName);
		ps.setString(2, empTel);
		ps.setString(3, empEmail);
		ps.setInt(4, empDeptId);
		ps.setString(5, username);
		ps.setString(6, userPwd);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//通过用户id修改用户密码
	public void updatePwdDAO(int empId, String pwd) throws SQLException {
		String sql = "update employee set userPwd = ? where empId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, pwd);
		ps.setInt(2, empId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//查询所有用户
	public ArrayList<Employee> selectAllUserDAO() throws SQLException {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		String sql = "select * from employee";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Employee employee = new Employee();
			employee.setEmpId(rs.getInt("empId"));
			employee.setEmpName(rs.getString("empName"));
			employee.setEmpTel(rs.getString("empTel"));
			employee.setEmpEmail(rs.getString("empEmail"));
			employee.setEmpDeptId(rs.getInt("empDeptId"));
			employee.setUsername(rs.getString("username"));
			employee.setUserPwd(rs.getString("userPwd"));
			employee.setUserStatus(rs.getInt("userStatus"));
			employee.setUserRole(rs.getInt("userRole"));
			employeeList.add(employee);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return employeeList;
	}
	
	
	//查询所有正在审核的用户
	public ArrayList<Employee> selectAllUnpassedUserDAO() throws SQLException {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		String sql = "select * from employee where userStatus = 0";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Employee employee = new Employee();
			employee.setEmpId(rs.getInt("empId"));
			employee.setEmpName(rs.getString("empName"));
			employee.setEmpTel(rs.getString("empTel"));
			employee.setEmpEmail(rs.getString("empEmail"));
			employee.setEmpDeptId(rs.getInt("empDeptId"));
			employee.setUsername(rs.getString("username"));
			employee.setUserPwd(rs.getString("userPwd"));
			employee.setUserStatus(rs.getInt("userStatus"));
			employee.setUserRole(rs.getInt("userRole"));
			employeeList.add(employee);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return employeeList;
	}
	
	//通过员工id找到员工
	public Employee selectUserByIdDAO(int empId) throws SQLException {
		Employee employee = null;
		String sql = "select * from employee where empId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			employee = new Employee();
			employee.setEmpId(rs.getInt("empId"));
			employee.setEmpName(rs.getString("empName"));
			employee.setEmpTel(rs.getString("empTel"));
			employee.setEmpEmail(rs.getString("empEmail"));
			employee.setEmpDeptId(rs.getInt("empDeptId"));
			employee.setUsername(rs.getString("username"));
			employee.setUserPwd(rs.getString("userPwd"));
			employee.setUserStatus(rs.getInt("userStatus"));
			employee.setUserRole(rs.getInt("userRole"));
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return employee;
	}
	
	//管理员修改员工信息
	public void updateEmpDAO(Employee emp) throws SQLException {
		String sql = "update employee set empName=?, empTel=?, empEmail=?, empDeptId=?, username=?, userPwd=?, userStatus=?, userRole=? where empId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, emp.getEmpName());
		ps.setString(2, emp.getEmpTel());
		ps.setString(3, emp.getEmpEmail());
		ps.setInt(4, emp.getEmpDeptId());
		ps.setString(5, emp.getUsername());
		ps.setString(6, emp.getUserPwd());
		ps.setInt(7, emp.getUserStatus());
		ps.setInt(8, emp.getUserRole());
		ps.setInt(9, emp.getEmpId());
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//通过id删除用户
	public void deleteEmpByIdDAO(int empId) throws SQLException {
		String sql = "delete from employee where empId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//管理员添加用户
	public void adminAddEmployeeDAO(Employee emp) throws SQLException {
		String sql = "INSERT INTO employee VALUES (empIdSeq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, emp.getEmpName());
		ps.setString(2, emp.getEmpTel());
		ps.setString(3, emp.getEmpEmail());
		ps.setInt(4, emp.getEmpDeptId());
		ps.setString(5, emp.getUsername());
		ps.setString(6, emp.getUserPwd());
		ps.setInt(7, emp.getUserStatus());
		ps.setInt(8, emp.getUserRole());
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	
	//通过员工id将会议和信息表中的信息删除
	public void deleteMeetingAndEmployeeByEmpIdDAO(int empId) throws SQLException {
		String sql = "delete from meetingAndEmployee where eId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//查询一共有多少员工
	public int employeesCountDAO() throws SQLException {
		int count = 0;
		String sql = "select count(*) from employee";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			count = Integer.parseInt(rs.getString(1));
		}
		ps.close();
		DBUtil.getConnection().close();
		return count;
	}
	
	//分页查询员工数据
	public ArrayList<Employee> selectEmployeesByPageDAO(int pageNum, int showNum) throws SQLException {
		ArrayList<Employee> al = new ArrayList<Employee>();
		String sql = "SELECT * FROM (SELECT e.*, ROWNUM rn FROM employee e) WHERE rn BETWEEN ? AND ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, (pageNum-1)*showNum + 1);
		ps.setInt(2, showNum*pageNum);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Employee um = new Employee(
					rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getInt(5), rs.getString(6),
					rs.getString(7), rs.getInt(8), rs.getInt(9)
					);
			al.add(um);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return al;
	}
	
	//通过员工姓名 用户名 状态查询所有员工
	public ArrayList<Employee> selectEmpByRulesDAO(String empName, String username, int userStatus) throws SQLException {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		String sql = "SELECT * FROM employee WHERE userStatus != -1";
		//如果员工姓名不为空 加上员工姓名的条件
		String empSql = "";
		if (empName != null && !"".equals(empName)) {
			empSql = " and empName like '%" + empName + "%'";
		} 
		//如果用户名不为空 加上用户名的条件
		String userSql = "";
		if (username != null && !"".equals(username)) {
			userSql = " and username like '%" + username + "%'";
		}
		
		//如果状态不为3 状态为3说明查找的是所有状态下的员工 不用判断状态
		String statusSql = "";
		if (userStatus != 3) {
			statusSql = " and userStatus = " + userStatus;
		}
		String sqlf = sql + empSql + userSql + statusSql;
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sqlf);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Employee employee = new Employee();
			employee.setEmpId(rs.getInt("empId"));
			employee.setEmpName(rs.getString("empName"));
			employee.setEmpTel(rs.getString("empTel"));
			employee.setEmpEmail(rs.getString("empEmail"));
			employee.setEmpDeptId(rs.getInt("empDeptId"));
			employee.setUsername(rs.getString("username"));
			employee.setUserPwd(rs.getString("userPwd"));
			employee.setUserStatus(rs.getInt("userStatus"));
			employee.setUserRole(rs.getInt("userRole"));
			employeeList.add(employee);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return employeeList;
	}
	
	//通过员工姓名 用户名 状态查询所有员工 分页
	public ArrayList<Employee> selectEmpByPageDAO(String empName, String username, int userStatus, int pageNum, int showNum) throws SQLException {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		String sql = "SELECT * FROM (SELECT e.*, ROWNUM rn FROM employee e WHERE userStatus != -1";
		//如果员工姓名不为空 加上员工姓名的条件
		String empSql = "";
		if (empName != null && !"".equals(empName)) {
			empSql = " and empName like '%" + empName + "%'";
		} 
		//如果用户名不为空 加上用户名的条件
		String userSql = "";
		if (username != null && !"".equals(username)) {
			userSql = " and username like '%" + username + "%'";
		}
		
		//如果状态不为3 状态为3说明查找的是所有状态下的员工 不用判断状态
		String statusSql = "";
		if (userStatus != 3) {
			statusSql = " and userStatus = " + userStatus;
		}
		
		
		String sqlf = sql + empSql + userSql + statusSql +  ")  where rn BETWEEN ? AND ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sqlf);
		ps.setInt(1, (pageNum-1)*showNum + 1);
		ps.setInt(2, showNum*pageNum);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Employee employee = new Employee();
			employee.setEmpId(rs.getInt("empId"));
			employee.setEmpName(rs.getString("empName"));
			employee.setEmpTel(rs.getString("empTel"));
			employee.setEmpEmail(rs.getString("empEmail"));
			employee.setEmpDeptId(rs.getInt("empDeptId"));
			employee.setUsername(rs.getString("username"));
			employee.setUserPwd(rs.getString("userPwd"));
			employee.setUserStatus(rs.getInt("userStatus"));
			employee.setUserRole(rs.getInt("userRole"));
			employeeList.add(employee);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return employeeList;
	}
	
	

	
	//通过员工id设置员工账号不通过审核 2
	public void notApproveByIdDAO(int empId) throws SQLException {
		String sql = "update employee set userStatus = 2 where empId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//通过员工id设置员工账号通过审核 1
	public void approveByIdDAO(int empId) throws SQLException {
		String sql = "update employee set userStatus = 1 where empId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	
	//通过部门id找到员工
	public ArrayList<Employee> selectUserByDeptIdDAO(int deptId) throws SQLException {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		String sql = "select * from employee where empDeptId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, deptId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Employee employee = new Employee();
			employee.setEmpId(rs.getInt("empId"));
			employee.setEmpName(rs.getString("empName"));
			employee.setEmpTel(rs.getString("empTel"));
			employee.setEmpEmail(rs.getString("empEmail"));
			employee.setEmpDeptId(rs.getInt("empDeptId"));
			employee.setUsername(rs.getString("username"));
			employee.setUserPwd(rs.getString("userPwd"));
			employee.setUserStatus(rs.getInt("userStatus"));
			employee.setUserRole(rs.getInt("userRole"));
			employeeList.add(employee);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return employeeList;
	}
	
	
	//员工自己修改自己的信息
	public void updateEmpByUserDAO(Employee emp) throws SQLException {
		String sql = "update employee set empName=?, empTel=?, empEmail=?, username=? where empId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, emp.getEmpName());
		ps.setString(2, emp.getEmpTel());
		ps.setString(3, emp.getEmpEmail());
		ps.setString(4, emp.getUsername());
		ps.setInt(5, emp.getEmpId());
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	public ArrayList<Employee> SelectEmployeesOfDeptDAO(int deptId) throws ClassNotFoundException, SQLException{
		ArrayList<Employee> al = new ArrayList<Employee>();
		DBUtil.getConnection();
		PreparedStatement ps =DBUtil.getConnection().prepareStatement("select empname,empid from employee where empdeptid=?");
		ps.setInt(1, deptId);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			Employee em = new Employee(rs.getString("empname"),rs.getInt("empid"));
			al.add(em);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return al;
	}
	
	public int SelectIdByNameDAO(String username) throws ClassNotFoundException, SQLException{
		DBUtil.getConnection();
		PreparedStatement ps =DBUtil.getConnection().prepareStatement("select empid from employee where username=?");
		ps.setString(1, username);
		ResultSet rs=ps.executeQuery();
		int id= 0;
		while(rs.next()){
			 id=rs.getInt("empid");
			
		}
		ps.close();
		rs.close();
		DBUtil.getConnection().close();
		return id;
	}
	
}

