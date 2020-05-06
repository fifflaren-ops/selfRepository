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
	@PostMapping
	@RequestMapping("list")
	@RequiresPermissions("normal:list")
	public List<Duty> dutiesList(){
		return dutyService.dutiesList();
	}
}
