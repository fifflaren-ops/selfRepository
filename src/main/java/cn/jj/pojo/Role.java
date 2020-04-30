package cn.jj.pojo;

import java.io.Serializable;


public class Role implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	private String roleName;
	private String permissionIds;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getPermissionIds() {
		return permissionIds;
	}
	public void setPermissionIds(String permissionIds) {
		this.permissionIds = permissionIds;
	}
	public Role(Integer id, String roleName, String permissionIds) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.permissionIds = permissionIds;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
