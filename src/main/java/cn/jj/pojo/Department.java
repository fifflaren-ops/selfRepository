package cn.jj.pojo;

public class Department {
	private Integer id;
	private String departmentName;
	private Integer parentId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Department(Integer id, String departmentName, Integer parentId) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.parentId = parentId;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
}
