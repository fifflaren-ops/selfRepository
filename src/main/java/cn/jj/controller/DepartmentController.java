package cn.jj.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import cn.jj.pojo.Department;
import cn.jj.service.DepartmentService;

@RestController
@RequestMapping("department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	@PostMapping
	@RequestMapping("parentList")
	@RequiresPermissions("normal:list")
	public List<Department> departmentsList(){
		return departmentService.parentDepartmentsList();
	}
	@GetMapping
	@RequestMapping("departmentList/{parentId}")
	public List<Department> getDepartmentsByParentId(@PathVariable("parentId") Integer parentId){
		return departmentService.getDepartmentsListByParentId(parentId);
	}
	@PostMapping
	@RequestMapping(value = "parentListPagination",consumes = "application/json")
	public PageInfo<Department> parentListPagination(@RequestBody Map<String, Object> map){
		return departmentService.parentListPagination(map);
	}
	@PostMapping
	@RequestMapping("checkdepartmentname")
	public Map<String, Boolean> checkdepartmentname(String departmentname){
		return departmentService.checkDepartmentByName(departmentname);
	}
	@PostMapping
	@RequestMapping("adddepartment")
	@RequiresPermissions("admin:add")
	public Integer addMember(@RequestBody Department department) {
		return departmentService.addDepartment(department);
	}
	@PostMapping
	@RequestMapping("editdepartment")
	@RequiresPermissions("admin:update")
	public Integer editDepartment(@RequestBody Department department) {
		return departmentService.editDepartment(department);
	}
	@GetMapping
	@RequestMapping("deldepartment/{id}")
	@RequiresPermissions("admin:delete")
	public Integer delMember(@PathVariable("id") Integer id) {
		return departmentService.delDepartmentById(id);
	}
}
