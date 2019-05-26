package com.icss.vo;

public class Counter {
	private int empId;
	private int counter;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public Counter(int empId, int counter) {
		super();
		this.empId = empId;
		this.counter = counter;
	}
	public Counter() {
		super();
	}
	@Override
	public String toString() {
		return "Counter [empId=" + empId + ", counter=" + counter + "]";
	}
	
}
