package cn.jj.pojo;



import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Member implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	private String realname;
	private String gender;
	private Integer age;
	private String phone;
	private Integer parentDepartmentNum;
	private Integer departmentNum;
	private String departmentName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinDate;
	private Integer dutyNum;
	private Integer role;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getParentDepartmentNum() {
		return parentDepartmentNum;
	}
	public void setParentDepartmentNum(Integer parentDepartmentNum) {
		this.parentDepartmentNum = parentDepartmentNum;
	}
	public Integer getDepartmentNum() {
		return departmentNum;
	}
	public void setDepartmentNum(Integer departmentNum) {
		this.departmentNum = departmentNum;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Integer getDutyNum() {
		return dutyNum;
	}
	public void setDutyNum(Integer dutyNum) {
		this.dutyNum = dutyNum;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Member(Integer id, String username, String password, String realname, String gender, Integer age,
			String phone, Integer parentDepartmentNum, Integer departmentNum, String departmentName, Date joinDate,
			Integer dutyNum, Integer role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.parentDepartmentNum = parentDepartmentNum;
		this.departmentNum = departmentNum;
		this.departmentName = departmentName;
		this.joinDate = joinDate;
		this.dutyNum = dutyNum;
		this.role = role;
	}
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
