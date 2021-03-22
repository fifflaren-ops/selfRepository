package cn.jj.service;

public interface RoleService {
	//根据角色ID获取权限
	String getPermissionIdsById(Integer id);
}
