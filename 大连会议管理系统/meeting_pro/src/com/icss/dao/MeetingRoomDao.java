package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.icss.util.DBUtil;
import com.icss.vo.MeetingRoom;

public class MeetingRoomDao {
	//获取所有的会议室
	public ArrayList<MeetingRoom> selectAllMrsDAO() throws SQLException {
		ArrayList<MeetingRoom> mrs = new ArrayList<MeetingRoom>();
		String sql = "SELECT * FROM meetingRoom order by mrId desc";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			MeetingRoom mr = new MeetingRoom(rs.getInt(1), rs.getInt(2),rs.getString(3),
					rs.getInt(4),rs.getInt(5),rs.getString(6));
			mrs.add(mr);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return mrs;
	}
	
	//添加会议室
	public void addMrDAO(MeetingRoom mr) throws SQLException {
		String sql = "INSERT INTO meetingRoom VALUES (mrIdSeq.nextval, ?, ?, ?, ?, ?)";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, mr.getMrNum());
		ps.setString(2, mr.getMrName());
		ps.setInt(3, mr.getMrCapacity());
		ps.setInt(4, mr.getMrStatus());
		ps.setString(5, mr.getMrRemark());
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//通过会议室id查找会议室
	public MeetingRoom selectMrByMrIdDAO(int mrId) throws SQLException {
		MeetingRoom mr = null;
		String sql = "SELECT * FROM meetingRoom where mrId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, mrId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			mr = new MeetingRoom(rs.getInt(1), rs.getInt(2),rs.getString(3),
					rs.getInt(4),rs.getInt(5),rs.getString(6));
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return mr;
	}
	
	//修改会议室信息
	public void updateMrDAO(MeetingRoom mr) throws SQLException {
		String sql = "update meetingRoom set mrNum = ?, mrName = ?, mrCapacity = ?, mrStatus= ?, mrRemark = ? where mrId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, mr.getMrNum());
		ps.setString(2, mr.getMrName());
		ps.setInt(3, mr.getMrCapacity());
		ps.setInt(4, mr.getMrStatus());
		ps.setString(5, mr.getMrRemark());
		ps.setInt(6, mr.getMrId());
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//通过开始时间和结束时间和会议室id判断该会议室是否被占用
	public boolean ifTheMRUsedDAO(String startTime, String endTime, int mrId) throws SQLException {
		boolean flag = false;
		String sql = "select * from meeting where ((to_date(?, 'yyyy-mm-dd hh24:mi:ss') >= startTime and to_date(?,'yyyy-mm-dd hh24:mi:ss') <= endTime) or (to_date(?,'yyyy-mm-dd hh24:mi:ss') >= startTime and to_date(?,'yyyy-mm-dd hh24:mi:ss') <= endTime) or (to_date(?, 'yyyy-mm-dd hh24:mi:ss') <= startTime and to_date(?,'yyyy-mm-dd hh24:mi:ss') >= startTime and to_date(?,'yyyy-mm-dd hh24:mi:ss') <= endTime) or(to_date(?,'yyyy-mm-dd hh24:mi:ss') <= startTime and  to_date(?,'yyyy-mm-dd hh24:mi:ss') >= endTime) or(to_date(?, 'yyyy-mm-dd hh24:mi:ss') >= startTime and to_date(?, 'yyyy-mm-dd hh24:mi:ss') <= endTime and to_date(?,'yyyy-mm-dd hh24:mi:ss') >= endTime)) and mrId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, startTime);
		ps.setString(2, startTime);
		ps.setString(3, endTime);
		ps.setString(4, endTime);
		ps.setString(5, startTime);
		ps.setString(6, endTime);
		ps.setString(7, endTime);
		ps.setString(8, startTime);
		ps.setString(9, endTime);
		ps.setString(10, startTime);
		ps.setString(11, startTime);
		ps.setString(12, endTime);
		ps.setInt(13, mrId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			flag = true;
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return flag;
	}
	
	
	//通过会议室名查询该会议室是否已经存在
	public boolean ifExistMrNameDAO(String mrName) throws SQLException {
		boolean flag = false;
		String sql = "select * from meetingRoom where mrName = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, mrName);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			flag = true;
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return flag;
	}
	
	//通过会议室id将会议室的状态设置为可用状态  0
	public void openMrDAO(int mrId) throws SQLException {
		String sql = "update meetingRoom set mrStatus= 0 where mrId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, mrId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}

	//通过会议室id将会议室的状态设置为不可用状态  1
	public void closeMrDAO(int mrId) throws SQLException {
		String sql = "update meetingRoom set mrStatus= 1 where mrId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, mrId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//通过会议室id判断是否有会议在使用该会议室
	//会议状态为未取消
	//开始时间大于等于现在的时间
	//结束时间大于等于现在的时间
	//我将来要用 或者我现在正在使用
	public boolean ifUsedMrByMeetingDAO(int mrId) throws SQLException {
		boolean flag = false;
		String sql = "select * from meeting where mrId = ? and mStatus = 0 and (startTime - SYSDATE >= 0 or endTime - SYSDATE >= 0)";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, mrId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			flag = true;
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return flag;
	}
		public void addMeetingRoomDAO(int mrnum,String mrname,int capacity,int mrstatus,String mrremark) throws ClassNotFoundException, SQLException{
			DBUtil.getConnection();
			PreparedStatement ps = DBUtil.getConnection().prepareStatement("insert into meetingroom values(mIdSeq.NEXTVAL,?,?,?,?,?)");
			ps.setInt(1, mrnum);
			ps.setString(2, mrname);
			ps.setInt(3, capacity);
			ps.setInt(4, mrstatus);
			ps.setString(5, mrremark);
			ps.executeUpdate();
			ps.close();
			DBUtil.getConnection().close();
		}
		public ArrayList<MeetingRoom> SelectAllMeeingRoomsByTimeDAO(Timestamp startime,Timestamp endtime) throws ClassNotFoundException, SQLException{
			ArrayList<MeetingRoom> al =new ArrayList<MeetingRoom>();
			DBUtil.getConnection();
			String start = startime.toString().substring(0,19);
			String end =endtime.toString().substring(0,19);
			/*PreparedStatement ps = DBUtil.connection().prepareStatement("select * from meetingroom"
					+ " where meetingroom.mrid not in"
					+ "(select mrid from meeting"
				+ " where(starttime<'"+start+"' and endtime >'"+end+"')"
				+ "or(starttime>'"+start+"' and starttime <'"+end+"')"
						+ "or(endtime>'"+start+"'and endtime<'"+end+"') and mstatus=0)");
			*/
			PreparedStatement ps = DBUtil.getConnection().prepareStatement("select * from meetingroom"
					+ " where meetingroom.mrid not in"
					+ "(select mrid from meeting"
					+ " where(starttime< to_date(?, 'yyyy-mm-dd hh24:mi:ss') and endtime >to_date(?, 'yyyy-mm-dd hh24:mi:ss'))"
					+ "or(starttime> to_date(?, 'yyyy-mm-dd hh24:mi:ss') and starttime <to_date(?, 'yyyy-mm-dd hh24:mi:ss'))"
							+ "or(endtime> to_date(?, 'yyyy-mm-dd hh24:mi:ss') and endtime<to_date(?, 'yyyy-mm-dd hh24:mi:ss'))) and mrstatus=0");
			ps.setString(1, start);
			ps.setString(2, end);
			ps.setString(3, start);
			ps.setString(4, end);
			ps.setString(5, start);
			ps.setString(6, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				MeetingRoom mr =new MeetingRoom(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6));
				al.add(mr);
			}
			rs.close();
			ps.close();
			DBUtil.getConnection().close();
			return al;
			
		}
	
}
