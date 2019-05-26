package com.icss.service;

import java.sql.SQLException;
import java.util.ArrayList;
import com.icss.dao.EmployeeDao;
import com.icss.vo.Employee;

public class EmployeeService {
	EmployeeDao ed = new EmployeeDao();

	//ͨ���û�����ѯ�Ƿ���ڸ��û�
	public boolean selectByNameService(String username) throws SQLException {
		boolean flag = false;
		if (ed.selectByNameDAO(username) != null) {
			flag = true;
		}
		return flag;
	}
		
	//ͨ���û����������ѯ���û�
	public Employee selectByNameAndPwdService(String username, String pwd) throws SQLException {
		return ed.selectByNameAndPwdDAO(username, pwd);
	}
	
	//ע���û�
	public void userRegisterService(String empName, String empTel, String empEmail,  int empDeptId, String username, String userPwd) throws SQLException {
		ed.userRegisterDAO(empName, empTel, empEmail, empDeptId, username, userPwd);
	}
	
	//ͨ���û�id�޸��û�����
	public void updatePwdService(int empId, String pwd) throws SQLException {
		ed.updatePwdDAO(empId, pwd);
	}
	
	
	//��ѯ�����û�
	public ArrayList<Employee> selectAllUserService() throws SQLException {
		return ed.selectAllUserDAO();
	}
	
	//��ѯ����������˵��û�
	public ArrayList<Employee> selectAllUnpassedUserService() throws SQLException {
		return ed.selectAllUnpassedUserDAO();
	}
	
	//ͨ��Ա��id�ҵ�Ա��
	public Employee selectUserByIdService(int empId) throws SQLException {
		return ed.selectUserByIdDAO(empId);
	}
	
	//����Ա�޸�Ա����Ϣ
	public void updateEmpService(Employee emp) throws SQLException {
		ed.updateEmpDAO(emp);
	}
	
	//ͨ��idɾ���û�
	public void deleteEmpByIdService(int empId) throws SQLException {
		ed.deleteEmpByIdDAO(empId);
	}
	
	//����Ա����û�
	public void adminAddEmployeeService(Employee emp) throws SQLException {
		ed.adminAddEmployeeDAO(emp);
	}
	
	//ͨ��Ա��id���������Ϣ���е���Ϣɾ��
	public void deleteMeetingAndEmployeeByEmpIdService(int empId) throws SQLException {
		ed.deleteMeetingAndEmployeeByEmpIdDAO(empId);
	}
	
	
	//��ѯһ���м�ҳ����
	public int employeesPageService(int showNum) throws SQLException {
		int count = ed.employeesCountDAO();
		int allPageNum = count % showNum == 0 ? count/showNum : count/showNum + 1;
		return allPageNum;
	}
	
	//��ѯһ���ж���Ա��
	public int employeesCountService() throws SQLException {
		return ed.employeesCountDAO();
	}
	
	//��ҳ��ѯԱ������
	public ArrayList<Employee> selectEmployeesByPageService(int pageNum, int showNum) throws SQLException {
		return ed.selectEmployeesByPageDAO(pageNum, showNum);
	}
	
	//ͨ��Ա��id����Ա���˺Ų�ͨ����� 2
	public void notApproveByIdService(int empId) throws SQLException {
		ed.notApproveByIdDAO(empId);
	}
	
	//ͨ��Ա��id����Ա���˺�ͨ����� 1
	public void approveByIdService(int empId) throws SQLException {
		ed.approveByIdDAO(empId);
	}
	
	//ͨ������id�ҵ�Ա��
	public ArrayList<Employee> selectUserByDeptIdService(int deptId) throws SQLException {
		return ed.selectUserByDeptIdDAO(deptId);
	}
	
	
	//Ա���Լ��޸��Լ�����Ϣ
	public void updateEmpByUserService(Employee emp) throws SQLException {
		ed.updateEmpByUserDAO(emp);
	}
	
	
	//ͨ��Ա������ �û��� ״̬��ѯ����Ա��
	public ArrayList<Employee> selectEmpByRulesService(String empName, String username, int userStatus) throws SQLException {
		return ed.selectEmpByRulesDAO(empName, username, userStatus);
	}
	
	//ͨ��Ա������ �û��� ״̬��ѯ����Ա�� ��ҳ
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
