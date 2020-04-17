package cn.jj.test;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.jj.service.DepartmentService;

@SpringBootTest
public class ClinicApplicationTest {
	@Autowired
	private DepartmentService departmentService;
	@Test
	void content() {
		Map<String, Boolean> checkDepartmentByName = departmentService.checkDepartmentByName("黄海");
		System.out.println(checkDepartmentByName.get("valid"));
	}
}
