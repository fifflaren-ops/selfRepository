package cn.jj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jj.pojo.Permission;

public interface PermissionMapper {
	List<Permission>getPermissionsByIds(@Param("ids")List<Integer>ids);
}
