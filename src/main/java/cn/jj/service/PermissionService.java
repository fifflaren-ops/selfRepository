package cn.jj.service;

import java.util.List;

import cn.jj.pojo.Permission;

public interface PermissionService {
	
	//根据权限ID获取权限对象
	List<Permission>getPermissionsByIds(List<Integer>ids);
}
