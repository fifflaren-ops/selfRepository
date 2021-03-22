package cn.jj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.jj.mapper.DepartmentMapper;
import cn.jj.pojo.Department;
import cn.jj.service.DepartmentService;
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	private DepartmentMapper departmentMapper;
	
	//获取父级部门列表
	@Override
	public List<Department> parentDepartmentsList() {
		return departmentMapper.parentDepartmentsList();
	}
	
	//根据父级部门ID获取下属的部门列表
	@Override
	public List<Department> getDepartmentsListByParentId(Integer parentId) {
		return departmentMapper.getDepartmentsListByParentId(parentId);
	}
	
	//分页操作
	@Override
	public PageInfo<Department> parentListPagination(Map<String, Object> map) {
		Integer pageNum = (Integer) map.get("pageNum");
		Integer pageSize = (Integer) map.get("pageSize");
		PageHelper.startPage(pageNum, pageSize);
		List<Department> parentDepartmentsList = departmentMapper.parentDepartmentsList();
		PageInfo<Department> pageInfo = new PageInfo<Department>(parentDepartmentsList);
		return pageInfo;
	}
	
	//根据部门名称检验部门是否存在,如果存在则返回key为valid,内容为true的hashmap,否则返回false
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
	
	//根据前端提交的信息封装成部门对象进行添加，如果添加成功则返回1并刷新页面
	@Override
	public Integer addDepartment(Department department) {
		return departmentMapper.addDepartment(department);
	}
	
	//根据部门ID获取部门
	@Override
	public Department getDepartmentById(Integer id) {
		return departmentMapper.getDepartmentById(id);
	}
	
	//根据前端提交的信息封装成部门对象进行编辑，如果编辑成功则返回1并刷新页面
	@Override
	public Integer editDepartment(Department department) {
		return departmentMapper.editDepartment(department);
	}
	
	//根据前端提交的部门ID删除部门
	@Override
	public Integer delDepartmentById(Integer id) {
		return departmentMapper.delDepartmentById(id);
	}
}
