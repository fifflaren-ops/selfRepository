package cn.jj.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.jj.pojo.Permission;
import cn.jj.service.DepartmentService;
import cn.jj.service.PermissionService;
import cn.jj.service.RoleService;

@SpringBootTest
public class ClinicApplicationTest {
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	@Test
	void content() {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		arrayList.add(4);
		List<Permission> permissionsByIds = permissionService.getPermissionsByIds(arrayList);
		for (Permission permission : permissionsByIds) {
			System.out.println(permission.getExpression());
		}
	}
}
