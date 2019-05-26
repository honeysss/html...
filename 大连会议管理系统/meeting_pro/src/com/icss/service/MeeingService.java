package com.icss.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.icss.dao.MeetingDao;
import com.icss.vo.Employee;
import com.icss.vo.Meeting;

public class MeeingService {
	MeetingDao md = new MeetingDao();
	//δ��������Ҫ�μӵĻ��� ͨ��Ա��id
	public ArrayList<Meeting> myMeetingsSevenDayService(int empId) throws SQLException {
		return md.myMeetingsSevenDayDAO(empId);
	}
	
	//�ҵ���ȡ���Ļ���
	public ArrayList<Meeting> myCancledMeetingsDAO(int empId) throws SQLException {
		return md.myCancledMeetingsDAO(empId);
	}
	
	
	//ͨ������id���һ���
	public Meeting selectMeetingByIdService(int mId) throws SQLException {
		return md.selectMeetingByIdDAO(mId);
	}
	
	//ͨ������id���Ҳμӻ����Ա��
	public ArrayList<Employee> selectEmpByMIdService(int mId) throws SQLException {
		return md.selectEmpByMIdDAO(mId);
	}
	
	//ͨ��Ա��id���Ҹ�Ա��Ԥ���Ļ���
	public ArrayList<Meeting> selectMeetingByEmpIdService(int empId) throws SQLException {
		return md.selectMeetingByEmpIdDAO(empId);
	}
	
	
	//ͨ������idȡ������ �������״̬����Ϊ1(ȡ��)
	public void cancleMeetingByMIdService(int mId, String cancleTime, String cancleReason) throws SQLException {
		md.cancleMeetingByMIdDAO(mId, cancleTime, cancleReason);
	}
	
	
	//ͨ��Ա��id���Ҹ�Ա�����μӵ����л���
	public ArrayList<Meeting> selectAllMeetingByEmpIdService(int empId) throws SQLException {
		return md.selectAllMeetingByEmpIdDAO(empId);
	}
	
	//��ӻ���
	public void addMeetingService(Meeting m, String reTime, String startTime, String endTime) throws SQLException {
		md.addMeetingDAO(m, reTime, startTime, endTime);
	}
	
	//�õ����������id
	public int newMeetingIdService() throws SQLException {
		return md.newMeetingIdDAO();
	}
	
	//ͨ��Ա��id�ͻ���id�������Ա��������������
	public void addToMAEService(int mId, int eId) throws SQLException {
		md.addToMAEDAO(mId, eId);
	}
	
	//�鿴���л���
	public ArrayList<Meeting> selectAllMeetingService() throws SQLException {
		return md.selectAllMeetingDAO();
	}
	
	
	//ͨ������id��Ա��id��ĳ��Ա���ӻ�����ɾ��
	public void deleteEmpFromMeetingService(int mId, int eId) throws SQLException {
		md.deleteEmpFromMeetingEDAO(mId, eId);
	}
	
	
	//ͨ������id��Ա��id��ĳ��Ա����ӵ�������
	public void addEmpToMeetingEService(int mId, int eId) throws SQLException {
		md.addEmpToMeetingEDAO(mId, eId);
	}
	
	//ͨ������id�޸Ļ���
	public void updateMeetingService(Meeting m, String startTime, String endTime) throws SQLException {
		md.updateMeetingDAO(m, startTime, endTime);
	}
	
	//ͨ��Ա��id�ͻ���id���Ҹ�Ա���Ƿ�μӹ����λ���
	public boolean ifAlreadyJoinedMeetingService(int mId, int eId) throws SQLException {
		return md.ifAlreadyJoinedMeetingDAO(mId, eId);
	}
	public void bookMeetingService(String mName,int mNum,int mrId,int reEmpId,Timestamp startTime, Timestamp endTime,String mDescribe) throws ClassNotFoundException, SQLException{
		md.bookMeetingDAO(mName, mNum, mrId, reEmpId,startTime, endTime, mDescribe);
	}
	public int selectMaxMeetingId() throws ClassNotFoundException, SQLException{
		return md.selectMaxMeetingIdDAO();
	}
	public void addMeetingEmployeeDAO(int mId,ArrayList<Integer> empidlist ) throws ClassNotFoundException, SQLException{
		for(Integer empid :empidlist)
		md.addMeetingEmployeeDAO(mId, empid);
	}
}
