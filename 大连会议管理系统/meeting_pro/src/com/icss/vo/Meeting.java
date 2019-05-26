package com.icss.vo;

import java.util.Date;

public class Meeting {
//	会议id 会议名称 预计参与人数  会议室id 预定人id 预定时间 开始时间  结束时间 取消时间  会议说明  会议状态默认为未取消1取消0
	private int mId;
	private String mName;
	private int mNum;
	private int mrId;
	private int reEmpId;
	private String reTime;
	private String startTime;
	private String endTime;
	private String cancleTime;
	private String mDescribe;
	private String cancleReason;
	private int mStatus;
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public int getMrId() {
		return mrId;
	}
	public void setMrId(int mrId) {
		this.mrId = mrId;
	}
	public int getReEmpId() {
		return reEmpId;
	}
	public void setReEmpId(int reEmpId) {
		this.reEmpId = reEmpId;
	}
	public String getReTime() {
		return reTime;
	}
	public void setReTime(String reTime) {
		this.reTime = reTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCancleTime() {
		return cancleTime;
	}
	public void setCancleTime(String cancleTime) {
		this.cancleTime = cancleTime;
	}
	public String getmDescribe() {
		return mDescribe;
	}
	public void setmDescribe(String mDescribe) {
		this.mDescribe = mDescribe;
	}
	public String getCancleReason() {
		return cancleReason;
	}
	public void setCancleReason(String cancleReason) {
		this.cancleReason = cancleReason;
	}
	public int getmStatus() {
		return mStatus;
	}
	public void setmStatus(int mStatus) {
		this.mStatus = mStatus;
	}
	public Meeting(int mId, String mName, int mNum, int mrId, int reEmpId, String reTime, String startTime,
			String endTime, String cancleTime, String mDescribe, String cancleReason, int mStatus) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.mNum = mNum;
		this.mrId = mrId;
		this.reEmpId = reEmpId;
		this.reTime = reTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.cancleTime = cancleTime;
		this.mDescribe = mDescribe;
		this.cancleReason = cancleReason;
		this.mStatus = mStatus;
	}
	public Meeting() {
		super();
	}
	@Override
	public String toString() {
		return "Meeting [mId=" + mId + ", mName=" + mName + ", mNum=" + mNum + ", mrId=" + mrId + ", reEmpId=" + reEmpId
				+ ", reTime=" + reTime + ", startTime=" + startTime + ", endTime=" + endTime + ", cancleTime="
				+ cancleTime + ", mDescribe=" + mDescribe + ", cancleReason=" + cancleReason + ", mStatus=" + mStatus
				+ "]";
	}
	
	
	
	
	
	
	
}
