package cn.jj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jj.pojo.Department;

public interface DepartmentMapper {
	List<Department>parentDepartmentsList();
	List<Department>getDepartmentsListByParentId(Integer parentId);
	Department checkDepartmentByName(@Param("departmentName")String departmentName);
	Integer addDepartment(@Param("department")Department department);
	Department getDepartmentById(Integer id);
	Integer editDepartment(@Param("department")Department department);
	Integer delDepartmentById(Integer id);
}
