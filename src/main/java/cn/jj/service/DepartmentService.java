package cn.jj.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import cn.jj.pojo.Department;

public interface DepartmentService {
	List<Department>parentDepartmentsList();
	List<Department>getDepartmentsListByParentId(Integer parentId);
	PageInfo<Department> parentListPagination(Map<String, Object> map);
	Map<String, Boolean> checkDepartmentByName(String departmentName);
	Integer addDepartment(Department department);
	Department getDepartmentById(Integer id);
	Integer editDepartment(Department department);
	Integer delDepartmentById(Integer id);
}
