package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.icss.util.DBUtil;
import com.icss.vo.Counter;

public class CounterDao {
	//ͨ���û�id���Ҹ��û��Ƿ���ʹ�����վ
	public Counter ifVisitedByIdDAO(int empId) throws SQLException {
		Counter count = null;
		String sql = "select * from counter where empId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs =  ps.executeQuery();
		while(rs.next()) {
			count = new Counter(rs.getInt(1), rs.getInt(2));
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return count;
	}
	
	//����ǵ�һ�ε�½ ��id�ʹ�����ӵ�counter��
	public void addCounterByIdAndCounterDAO(int empId, int counter) throws SQLException {
		String sql = "insert into counter values(?, ?)";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ps.setInt(2, counter);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//ͨ��Ա��id�����÷��ʴ���
	public void setCounterByIdDAO(int empId, int counter) throws SQLException {
		String sql = "update counter set counter = ? where empId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, counter);
		ps.setInt(2, empId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	//ͨ���û�idɾ��counter�е�����
	public void deleteCounterByIdDAO(int empId) throws SQLException {
		String sql = "delete from counter where empId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	
}
