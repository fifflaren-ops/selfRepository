package cn.jj.mapper;

public interface RoleMapper {
	//根据角色ID获取权限
	String getPermissionIdsById(Integer id);
}
 