package cn.jj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.jj.mapper.DepartmentMapper;
import cn.jj.pojo.Department;
import cn.jj.service.DepartmentService;
@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	private DepartmentMapper departmentMapper;
	@Override
	public List<Department> parentDepartmentsList() {
		return departmentMapper.parentDepartmentsList();
	}
	@Override
	public List<Department> getDepartmentsListByParentId(Integer parentId) {
		return departmentMapper.getDepartmentsListByParentId(parentId);
	}
	@Override
	public PageInfo<Department> parentListPagination(Map<String, Object> map) {
		Integer pageNum = (Integer) map.get("pageNum");
		Integer pageSize = (Integer) map.get("pageSize");
		PageHelper.startPage(pageNum, pageSize);
		List<Department> parentDepartmentsList = departmentMapper.parentDepartmentsList();
		PageInfo<Department> pageInfo = new PageInfo<Department>(parentDepartmentsList);
		return pageInfo;
	}
	@Override
	public Map<String, Boolean> checkDepartmentByName(String departmentName) {
		HashMap<String,Boolean> hashMap = new HashMap<String, Boolean>();
		Department department = departmentMapper.checkDepartmentByName(departmentName);
		if (department==null) {
			hashMap.put("valid", true);
		}else {
			hashMap.put("valid", false);
		}
		return hashMap;
	}
	@Override
	public Integer addDepartment(Department department) {
		return departmentMapper.addDepartment(department);
	}
	@Override
	public Department getDepartmentById(Integer id) {
		return departmentMapper.getDepartmentById(id);
	}
	@Override
	public Integer editDepartment(Department department) {
		return departmentMapper.editDepartment(department);
	}
	@Override
	public Integer delDepartmentById(Integer id) {
		return departmentMapper.delDepartmentById(id);
	}
}
