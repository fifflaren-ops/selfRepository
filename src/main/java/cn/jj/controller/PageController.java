package cn.jj.controller;


import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jj.pojo.Department;
import cn.jj.pojo.Duty;
import cn.jj.pojo.Member;
import cn.jj.service.DepartmentService;
import cn.jj.service.DutyService;
import cn.jj.service.MemberService;

//页面控制器
@Controller
public class PageController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private DutyService dutyService;
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	@RequestMapping("welcome")
	public String welcome() {
		return "welcome";
	}
	@RequestMapping("unauthorized")
	public String unauthorized() {
		return "unauthorized";
	}
	@RequestMapping("tologin")
	public String login() {
		return "login";
	}
	@RequestMapping("memberTable")
	@RequiresPermissions("normal:list")
	public String memberTable() {
		return "memberTable";
	}
	@RequestMapping("editMember/{id}")
	@RequiresPermissions("admin:update")
	public String editMember(@PathVariable(name = "id") Integer id,Model model) {
		Member member = memberService.getMemberById(id);
		List<Department> parentDepartmentsList = departmentService.parentDepartmentsList();
		List<Department> departmentsList = departmentService.getDepartmentsListByParentId(member.getParentDepartmentNum());
		List<Duty> dutiesList = dutyService.dutiesList();
		model.addAttribute("member", member);
		model.addAttribute("parentDepartmentsList", parentDepartmentsList);
		model.addAttribute("departmentsList", departmentsList);
		model.addAttribute("dutiesList", dutiesList);
		return "editMember";
	}
	@RequestMapping("/departmenttable")
	@RequiresPermissions("normal:list")
	public String departmentTable() {
		return "departmentTable";
	}
	@RequestMapping("editDepartment/{id}")
	@RequiresPermissions("admin:update")
	public String editDepartment(@PathVariable(name = "id")Integer id,Model model) {
		Department department = departmentService.getDepartmentById(id);
		List<Department> parentDepartmentsList = departmentService.parentDepartmentsList();
		model.addAttribute("department", department);
		model.addAttribute("parentDepartmentsList", parentDepartmentsList);
		return "editDepartment";
	}
}
