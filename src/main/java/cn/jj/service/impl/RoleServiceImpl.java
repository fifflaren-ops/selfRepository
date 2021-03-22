package cn.jj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jj.mapper.RoleMapper;
import cn.jj.service.RoleService;
@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;
	//根据角色ID获取权限
	@Override
	public String getPermissionIdsById(Integer id) {
		return roleMapper.getPermissionIdsById(id);
	}

}
