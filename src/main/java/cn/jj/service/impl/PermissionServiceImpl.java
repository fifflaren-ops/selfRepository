package cn.jj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jj.mapper.PermissionMapper;
import cn.jj.pojo.Permission;
import cn.jj.service.PermissionService;
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	//根据权限ID获取权限对象
	@Override
	public List<Permission> getPermissionsByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		return permissionMapper.getPermissionsByIds(ids);
	}

}
