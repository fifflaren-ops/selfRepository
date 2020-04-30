package cn.jj.service;

import java.util.List;

import cn.jj.pojo.Permission;

public interface PermissionService {
	List<Permission>getPermissionsByIds(List<Integer>ids);
}
