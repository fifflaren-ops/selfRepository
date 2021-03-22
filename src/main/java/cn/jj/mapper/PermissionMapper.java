package cn.jj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jj.pojo.Permission;

public interface PermissionMapper {
	
	//根据权限ID获取权限对象
	List<Permission>getPermissionsByIds(@Param("ids")List<Integer>ids);
}
