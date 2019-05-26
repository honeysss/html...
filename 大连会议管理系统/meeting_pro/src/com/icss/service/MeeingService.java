package com.icss.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.icss.dao.MeetingDao;
import com.icss.vo.Employee;
import com.icss.vo.Meeting;

public class MeeingService {
	MeetingDao md = new MeetingDao();
	//未来七天我要参加的会议 通过员工id
	public ArrayList<Meeting> myMeetingsSevenDayService(int empId) throws SQLException {
		return md.myMeetingsSevenDayDAO(empId);
	}
	
	//我的已取消的会议
	public ArrayList<Meeting> myCancledMeetingsDAO(int empId) throws SQLException {
		return md.myCancledMeetingsDAO(empId);
	}
	
	
	//通过会议id查找会议
	public Meeting selectMeetingByIdService(int mId) throws SQLException {
		return md.selectMeetingByIdDAO(mId);
	}
	
	//通过会议id查找参加会议的员工
	public ArrayList<Employee> selectEmpByMIdService(int mId) throws SQLException {
		return md.selectEmpByMIdDAO(mId);
	}
	
	//通过员工id查找该员工预定的会议
	public ArrayList<Meeting> selectMeetingByEmpIdService(int empId) throws SQLException {
		return md.selectMeetingByEmpIdDAO(empId);
	}
	
	
	//通过会议id取消会议 将会议的状态设置为1(取消)
	public void cancleMeetingByMIdService(int mId, String cancleTime, String cancleReason) throws SQLException {
		md.cancleMeetingByMIdDAO(mId, cancleTime, cancleReason);
	}
	
	
	//通过员工id查找该员工将参加的所有会议
	public ArrayList<Meeting> selectAllMeetingByEmpIdService(int empId) throws SQLException {
		return md.selectAllMeetingByEmpIdDAO(empId);
	}
	
	//添加会议
	public void addMeetingService(Meeting m, String reTime, String startTime, String endTime) throws SQLException {
		md.addMeetingDAO(m, reTime, startTime, endTime);
	}
	
	//得到会议的最新id
	public int newMeetingIdService() throws SQLException {
		return md.newMeetingIdDAO();
	}
	
	//通过员工id和会议id往会议个员工表中增加数据
	public void addToMAEService(int mId, int eId) throws SQLException {
		md.addToMAEDAO(mId, eId);
	}
	
	//查看所有会议
	public ArrayList<Meeting> selectAllMeetingService() throws SQLException {
		return md.selectAllMeetingDAO();
	}
	
	
	//通过会议id和员工id将某个员工从会议中删除
	public void deleteEmpFromMeetingService(int mId, int eId) throws SQLException {
		md.deleteEmpFromMeetingEDAO(mId, eId);
	}
	
	
	//通过会议id和员工id将某个员工添加到会议中
	public void addEmpToMeetingEService(int mId, int eId) throws SQLException {
		md.addEmpToMeetingEDAO(mId, eId);
	}
	
	//通过会议id修改会议
	public void updateMeetingService(Meeting m, String startTime, String endTime) throws SQLException {
		md.updateMeetingDAO(m, startTime, endTime);
	}
	
	//通过员工id和会议id查找该员工是否参加过本次会议
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
