package cn.jj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jj.mapper.PermissionMapper;
import cn.jj.pojo.Permission;
import cn.jj.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public List<Permission> getPermissionsByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		return permissionMapper.getPermissionsByIds(ids);
	}

}
