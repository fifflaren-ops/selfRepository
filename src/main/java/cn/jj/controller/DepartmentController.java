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
	
	/*
	 * 获取部门列表
	 * 需要‘normal:list’的权限
	 * 请求方式为post
	 * 请求地址为localhost:8080/clinic/department/parentList
	 */
	@PostMapping
	@RequestMapping("parentList")
	@RequiresPermissions("normal:list")
	public List<Department> departmentsList(){
		return departmentService.parentDepartmentsList();
	}
	
	/*
	 * 根据父级部门ID来获取下属的部门列表
	 * 请求方式为get
	 * 请求地址为localhost:8080/clinic/department/departmentList/父级部门ID
	 * 需要‘normal:list’的权限
	 */
	@GetMapping
	@RequestMapping("departmentList/{parentId}")
	@RequiresPermissions("normal:list")
	public List<Department> getDepartmentsByParentId(@PathVariable("parentId") Integer parentId){
		return departmentService.getDepartmentsListByParentId(parentId);
	}
	
	/*
	 * 分页请求
	 * 处理前端传来的json数据
	 * 请求方式为post
	 * 请求地址为localhost:8080/clinic/department/parentListPagination
	 * 需要‘normal:list’的权限
	 */
	@PostMapping
	@RequestMapping(value = "parentListPagination",consumes = "application/json")
	@RequiresPermissions("normal:list")
	public PageInfo<Department> parentListPagination(@RequestBody Map<String, Object> map){
		return departmentService.parentListPagination(map);
	}
	
	/*
	 * 验证部门名是否已存在
	 * 请求方式为post
	 * 请求地址为localhost:8080/clinic/department/checkdepartmentname
	 */
	@PostMapping
	@RequestMapping("checkdepartmentname")
	public Map<String, Boolean> checkdepartmentname(String departmentname){
		return departmentService.checkDepartmentByName(departmentname);
	}
	
	/*
	 * 添加部门
	 * 请求方式为post
	 * 需要‘admnin：add’的权限
	 * 请求地址为localhost:8080/clinic/department/adddepartment
	 */
	@PostMapping
	@RequestMapping("adddepartment")
	@RequiresPermissions("admin:add")
	public Integer addMember(@RequestBody Department department) {
		return departmentService.addDepartment(department);
	}
	
	/*
	 * 编辑部门信息
	 * 请求方式为post
	 * 需要‘admin：update’的权限
	 * 请求地址为localhost:8080/clinic/department/editdepartment
	 */
	@PostMapping
	@RequestMapping("editdepartment")
	@RequiresPermissions("admin:update")
	public Integer editDepartment(@RequestBody Department department) {
		return departmentService.editDepartment(department);
	}
	
	/*
	 * 根据成员ID删除成员
	 * 请求方式为get
	 * 需要‘admin：delete’的权限
	 * 请求地址为localhost:8080/clinic/department/deldepartment/成员ID
	 */
	@GetMapping
	@RequestMapping("deldepartment/{id}")
	@RequiresPermissions("admin:delete")
	public Integer delMember(@PathVariable("id") Integer id) {
		return departmentService.delDepartmentById(id);
	}
}
