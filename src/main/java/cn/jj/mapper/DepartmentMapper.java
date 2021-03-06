package cn.jj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jj.pojo.Department;

public interface DepartmentMapper {
	//获取父级部门列表
	List<Department>parentDepartmentsList();
	
	//根据父级部门ID获取下属的部门列表
	List<Department>getDepartmentsListByParentId(Integer parentId);
	
	//根据部门名称检验部门是否存在
	Department checkDepartmentByName(@Param("departmentName")String departmentName);
	
	//根据前端提交的信息封装成部门对象进行添加，如果添加成功则返回1并刷新页面
	Integer addDepartment(@Param("department")Department department);
	
	//根据部门ID获取部门
	Department getDepartmentById(Integer id);
	
	//根据前端提交的信息封装成部门对象进行编辑，如果编辑成功则返回1并刷新页面
	Integer editDepartment(@Param("department")Department department);
	
	//根据前端提交的部门ID删除部门
	Integer delDepartmentById(Integer id);
}
