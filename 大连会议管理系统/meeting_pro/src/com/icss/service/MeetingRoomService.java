package com.icss.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


import com.icss.dao.MeetingRoomDao;
import com.icss.vo.MeetingRoom;

public class MeetingRoomService {
	MeetingRoomDao mrd = new MeetingRoomDao();
	//��ȡ���еĻ�����
	public ArrayList<MeetingRoom> selectAllMrsService() throws SQLException {
		return mrd.selectAllMrsDAO();
	}
	
	//��ӻ�����
	public void addMrService(MeetingRoom mr) throws SQLException {
		mrd.addMrDAO(mr);
	}
	
	//ͨ��������id���һ�����
	public MeetingRoom selectMrByMrIdService(int mrId) throws SQLException {
		return mrd.selectMrByMrIdDAO(mrId);
	}
	
	//�޸Ļ�������Ϣ
	public void updateMrService(MeetingRoom mr) throws SQLException {
		mrd.updateMrDAO(mr);
	}
	
	
	//ͨ����ʼʱ��ͽ���ʱ��ͻ�����id�жϸû������Ƿ�ռ��
	public boolean ifTheMRUsedService(String startTime, String endTime, int mrId) throws SQLException {
		return mrd.ifTheMRUsedDAO(startTime, endTime, mrId);
	}
	
	//ͨ������������ѯ�û������Ƿ��Ѿ�����
	public boolean ifExistMrNameService(String mrName) throws SQLException {
		return mrd.ifExistMrNameDAO(mrName);
	}
	
	//ͨ��������id�������ҵ�״̬����Ϊ����״̬  0
	public void openMrService(int mrId) throws SQLException {
		mrd.openMrDAO(mrId);
	}
	
	//ͨ��������id�������ҵ�״̬����Ϊ������״̬  1
	public void closeMrService(int mrId) throws SQLException {
		mrd.closeMrDAO(mrId);
	}
	
	
	//ͨ��������id�ж��Ƿ��л�����ʹ�øû�����
	//����״̬Ϊδȡ��
	//��ʼʱ����ڵ������ڵ�ʱ��
	//����ʱ����ڵ������ڵ�ʱ��
	//�ҽ���Ҫ�� ��������������ʹ��
	public boolean ifUsedMrByMeetingService(int mrId) throws SQLException {
		return mrd.ifUsedMrByMeetingDAO(mrId);
	}
	public ArrayList<MeetingRoom> SelectAllMeeingRoomsByTimeService(Timestamp startime,Timestamp endtime) throws ClassNotFoundException, SQLException{
		return mrd.SelectAllMeeingRoomsByTimeDAO(startime, endtime);
		
	}
	
}
