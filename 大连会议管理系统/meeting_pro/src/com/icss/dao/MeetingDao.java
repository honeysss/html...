package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.icss.util.DBUtil;
import com.icss.vo.Employee;
import com.icss.vo.Meeting;

public class MeetingDao {
	//δ��������Ҫ�μӵĻ��� ͨ��Ա��id ��������
	public ArrayList<Meeting> myMeetingsSevenDayDAO(int empId) throws SQLException {
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		String sql = "SELECT m.* FROM employee e, meeting m, Meetingandemployee mae WHERE e.empId = mae.eId AND m.mId = mae.mId and e.empId = ? AND m.startTime - SYSDATE < 7 AND m.startTime - SYSDATE > 0 and m.mStatus = 0 order by startTime";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Meeting meeting = new Meeting(rs.getInt(1), rs.getString(2),rs.getInt(3),
					rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
					rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12));
			meetings.add(meeting);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return meetings;
	}
	
	//�ҵ���ȡ���Ļ���
	public ArrayList<Meeting> myCancledMeetingsDAO(int empId) throws SQLException {
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		String sql = "SELECT m.* FROM employee e, meeting m, Meetingandemployee mae WHERE e.empId = mae.eId AND m.mId = mae.mId and e.empId = ? and m.mStatus = 1 order by startTime";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Meeting meeting = new Meeting(rs.getInt(1), rs.getString(2),rs.getInt(3),
					rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
					rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12));
			meetings.add(meeting);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return meetings;
	}
	
	//ͨ������id���һ���
	public Meeting selectMeetingByIdDAO(int mId) throws SQLException {
		Meeting meeting = null;
		String sql = "select * from meeting where mId = ? order by startTime";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			meeting = new Meeting(rs.getInt(1), rs.getString(2),rs.getInt(3),
					rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
					rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12));
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return meeting;
	}
	
	//ͨ������id���Ҳμӻ����Ա��
	public ArrayList<Employee> selectEmpByMIdDAO(int mId) throws SQLException {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		String sql = "SELECT e.* FROM employee e, meeting m, Meetingandemployee mae WHERE e.empId = mae.eId AND m.mId = mae.mId AND m.mId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
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
			empList.add(employee);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return empList;
	}
	
	//ͨ��Ա��id���Ҹ�Ա��Ԥ���Ļ���
	public ArrayList<Meeting> selectMeetingByEmpIdDAO(int empId) throws SQLException {
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		String sql = "select * from meeting where reEmpId = ? order by startTime";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Meeting meeting = new Meeting(rs.getInt(1), rs.getString(2),rs.getInt(3),
					rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
					rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12));
			meetings.add(meeting);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return meetings;
	}
	
	//ͨ������idȡ������ �������״̬����Ϊ1(ȡ��) ������ȡ��ʱ��
	public void cancleMeetingByMIdDAO(int mId, String cancleTime, String cancleReason) throws SQLException {
		String sql = "update meeting set mStatus = 1, cancleTime = to_date(?,'yyyy-mm-dd hh24:mi:ss'), cancleReason = ? where mId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, cancleTime);
		ps.setString(2, cancleReason);
		ps.setInt(3, mId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//ͨ��Ա��id���Ҹ�Ա�����μӵ����л���
	public ArrayList<Meeting> selectAllMeetingByEmpIdDAO(int empId) throws SQLException {
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		String sql = "SELECT m.* FROM employee e, meeting m, Meetingandemployee mae WHERE e.empId = mae.eId AND m.mId = mae.mId and e.empId = ? and mStatus = 0 AND m.startTime - SYSDATE > 0  order by startTime";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Meeting meeting = new Meeting(rs.getInt(1), rs.getString(2),rs.getInt(3),
					rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
					rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12));
			meetings.add(meeting);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return meetings;
	}
	
	
	//��ӻ���
	public void addMeetingDAO(Meeting m, String reTime, String startTime, String endTime) throws SQLException {
		String sql = "INSERT INTO meeting VALUES(mIdSeq.NEXTVAL, ?, ?, ?, ?, to_date(?,'yyyy-mm-dd hh24:mi:ss'), to_date(?,'yyyy-mm-dd hh24:mi:ss'), to_date(?,'yyyy-mm-dd hh24:mi:ss'), null, ?, null, 0)";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, m.getmName());
		ps.setInt(2, m.getmNum());
		ps.setInt(3, m.getMrId());
		ps.setInt(4, m.getReEmpId());
		ps.setString(5, reTime);
		ps.setString(6, startTime);
		ps.setString(7, endTime);
		ps.setString(8, m.getmDescribe());
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//�õ����������id
	public int newMeetingIdDAO() throws SQLException {
		int mId = 0;
		String sql = "select mId from (select mId from meeting order by mId desc) where rownum = 1";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			mId = rs.getInt(1);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return mId;
	}
	
	//ͨ��Ա��id�ͻ���id�������Ա��������������
	public void addToMAEDAO(int mId, int eId) throws SQLException {
		String sql = "insert into meetingAndEmployee values(?, ?)";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
		ps.setInt(2, eId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//�鿴���л���
	public ArrayList<Meeting> selectAllMeetingDAO() throws SQLException {
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		String sql = "SELECT * from meeting order by startTime";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Meeting meeting = new Meeting(rs.getInt(1), rs.getString(2),rs.getInt(3),
					rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
					rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12));
			meetings.add(meeting);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return meetings;
	}
	
	
	//ͨ������id��Ա��id��ĳ��Ա���ӻ�����ɾ��
	public void deleteEmpFromMeetingEDAO(int mId, int eId) throws SQLException {
		String sql = "delete from meetingAndEmployee where mId = ? and eId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
		ps.setInt(2, eId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	//ͨ������id��Ա��id��ĳ��Ա����ӵ�������
	public void addEmpToMeetingEDAO(int mId, int eId) throws SQLException {
		String sql = "insert into meetingAndEmployee values(?, ?)";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
		ps.setInt(2, eId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	
	//ͨ������id�޸Ļ���
	public void updateMeetingDAO(Meeting m, String startTime, String endTime) throws SQLException {
		String sql = "update meeting set mName = ?, mNum = ?, mrId = ?, startTime = to_date(?,'yyyy-mm-dd hh24:mi:ss'), endTime = to_date(?,'yyyy-mm-dd hh24:mi:ss'), mDescribe = ? where mId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, m.getmName());
		ps.setInt(2, m.getmNum());
		ps.setInt(3, m.getMrId());
		ps.setString(4, startTime);
		ps.setString(5, endTime);
		ps.setString(6, m.getmDescribe());
		ps.setInt(7, m.getmId());
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
	
	
	//ͨ��Ա��id�ͻ���id���Ҹ�Ա���Ƿ�μӹ����λ���
	public boolean ifAlreadyJoinedMeetingDAO(int mId, int eId) throws SQLException {
		boolean flag = false;
		String sql = "select * from meetingAndEmployee where mId = ? and eId = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
		ps.setInt(2, eId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			flag = true;
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return flag;
	}
	
	public void bookMeetingDAO(String mName,int mNum,int mrId,int reEmpId,Timestamp startTime, Timestamp endTime, String mDescribe ) throws ClassNotFoundException, SQLException{
		DBUtil.getConnection();
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("insert into meeting values(mIdSeq.NEXTVAL,?,?,?,?,sysdate,?,?,null,?,null,0)");
		ps.setString(1,mName );
		ps.setInt(2,mNum );
		ps.setInt(3, mrId);
		ps.setInt(4, reEmpId);
		ps.setTimestamp(5,startTime );
		ps.setTimestamp(6, endTime);
		ps.setString(7,mDescribe );
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
		
	}
	public int selectMaxMeetingIdDAO() throws ClassNotFoundException, SQLException{
		DBUtil.getConnection();
		PreparedStatement ps =DBUtil.getConnection().prepareStatement("select max(mid) from meeting");
		ResultSet rs =ps.executeQuery();
		int mid=0;
		while(rs.next()){
			mid=rs.getInt(1);
			
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return mid;
		
	}
	public void addMeetingEmployeeDAO(int mId,int eId) throws ClassNotFoundException, SQLException{
		DBUtil.getConnection();
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("insert into meetingandemployee values(?,?)");
		ps.setInt(1, mId);
		ps.setInt(2, eId);
		ps.executeUpdate();
		ps.close();
		DBUtil.getConnection().close();
	}
}
