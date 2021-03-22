package cn.jj.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.jj.pojo.Duty;
import cn.jj.service.DutyService;

@RestController
@RequestMapping("duty")
public class DutyController {
	@Autowired
	private DutyService dutyService;
	
	/*
	 * 获取职位列表
	 * 需要‘normal:list’的权限
	 * 请求方式为post
	 * 请求地址为localhost:8080/clinic/duty/list
	 */
	@PostMapping
	@RequestMapping("list")
	@RequiresPermissions("normal:list")
	public List<Duty> dutiesList(){
		return dutyService.dutiesList();
	}
}
