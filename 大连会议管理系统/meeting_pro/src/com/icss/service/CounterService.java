package com.icss.service;

import java.sql.SQLException;

import com.icss.dao.CounterDao;
import com.icss.vo.Counter;

public class CounterService {
	CounterDao cd = new CounterDao();
	//ͨ���û�id���Ҹ��û��Ƿ���ʹ�����վ
	public Counter ifVisitedByIdService(int empId) throws SQLException {
		return cd.ifVisitedByIdDAO(empId);
	}
	
	//����ǵ�һ�ε�½ ��id�ʹ�����ӵ�counter��
	public void addCounterByIdAndCounterService(int empId, int counter) throws SQLException {
		cd.addCounterByIdAndCounterDAO(empId, counter);
	}
	
	//ͨ��Ա��id���÷�������
	public void setCounterByIdService(int empId, int counter) throws SQLException {
		cd.setCounterByIdDAO(empId, counter);
	}
	
	//ͨ���û�idɾ��counter�е�����
	public void deleteCounterByIdService(int empId) throws SQLException {
		cd.deleteCounterByIdDAO(empId);
	}
	
}
