package cn.jj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jj.mapper.RoleMapper;
import cn.jj.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public String getPermissionIdsById(Integer id) {
		return roleMapper.getPermissionIdsById(id);
	}

}
