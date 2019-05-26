package com.icss.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


import com.icss.dao.MeetingRoomDao;
import com.icss.vo.MeetingRoom;

public class MeetingRoomService {
	MeetingRoomDao mrd = new MeetingRoomDao();
	//获取所有的会议室
	public ArrayList<MeetingRoom> selectAllMrsService() throws SQLException {
		return mrd.selectAllMrsDAO();
	}
	
	//添加会议室
	public void addMrService(MeetingRoom mr) throws SQLException {
		mrd.addMrDAO(mr);
	}
	
	//通过会议室id查找会议室
	public MeetingRoom selectMrByMrIdService(int mrId) throws SQLException {
		return mrd.selectMrByMrIdDAO(mrId);
	}
	
	//修改会议室信息
	public void updateMrService(MeetingRoom mr) throws SQLException {
		mrd.updateMrDAO(mr);
	}
	
	
	//通过开始时间和结束时间和会议室id判断该会议室是否被占用
	public boolean ifTheMRUsedService(String startTime, String endTime, int mrId) throws SQLException {
		return mrd.ifTheMRUsedDAO(startTime, endTime, mrId);
	}
	
	//通过会议室名查询该会议室是否已经存在
	public boolean ifExistMrNameService(String mrName) throws SQLException {
		return mrd.ifExistMrNameDAO(mrName);
	}
	
	//通过会议室id将会议室的状态设置为可用状态  0
	public void openMrService(int mrId) throws SQLException {
		mrd.openMrDAO(mrId);
	}
	
	//通过会议室id将会议室的状态设置为不可用状态  1
	public void closeMrService(int mrId) throws SQLException {
		mrd.closeMrDAO(mrId);
	}
	
	
	//通过会议室id判断是否有会议在使用该会议室
	//会议状态为未取消
	//开始时间大于等于现在的时间
	//结束时间大于等于现在的时间
	//我将来要用 或者我现在正在使用
	public boolean ifUsedMrByMeetingService(int mrId) throws SQLException {
		return mrd.ifUsedMrByMeetingDAO(mrId);
	}
	public ArrayList<MeetingRoom> SelectAllMeeingRoomsByTimeService(Timestamp startime,Timestamp endtime) throws ClassNotFoundException, SQLException{
		return mrd.SelectAllMeeingRoomsByTimeDAO(startime, endtime);
		
	}
	
}
