package cn.jj.pojo;

import java.io.Serializable;

public class Duty implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String dutyName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDutyName() {
		return dutyName;
	}
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	public Duty(Integer id, String dutyName) {
		super();
		this.id = id;
		this.dutyName = dutyName;
	}
	public Duty() {
		super();
		// TODO Auto-generated constructor stub
	}
}
