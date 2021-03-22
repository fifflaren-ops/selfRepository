package cn.jj.controller;

import java.util.HashMap;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.jj.pojo.Member;
import cn.jj.service.MemberService;

@RestController
@RequestMapping("member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	/*
	 * 获取成员列表并进行分页
	 * 处理前端传来的json数据
	 * 请求方式为post
	 * 请求地址为localhost:8080/clinic/department/member/list
	 * 需要‘normal:list’的权限
	 * 返回用户信息列表
	 */
	@PostMapping
	@RequestMapping(value = "list",consumes = "application/json")
	@RequiresPermissions("normal:list")
	public PageInfo<Member> doctorsList(@RequestBody Map<String, Object> map){
		Integer pageNum = (Integer) map.get("pageNum");
		Integer pageSize = (Integer) map.get("pageSize");
		String keyword = (String) map.get("keyword");
		PageHelper.startPage(pageNum, pageSize);
		List<Member> personsList = memberService.personsList(keyword);
		PageInfo<Member> pageInfo = new PageInfo<Member>(personsList);
		return pageInfo;
	}
	
	/*
	 * 根据用户名检验用户名存在
	 * 请求方式为post
	 * 请求地址为localhost:8080/clinic/member/checkusername
	 * 返回一个只有一个key为valid，值为true或false的hashmap，true说明请求输入进来的username可用
	 * 否则则说明请求输入进来的username不可用
	 */
	@PostMapping
	@RequestMapping("checkusername")
	public HashMap<String, Boolean> checkUsername(String username) {
		HashMap<String,Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("valid", memberService.checkMemberUsername(username));
		return hashMap;
	}
	
	/*
	 * 根据真实姓名检验该真实姓名是否已存在
	 * 请求方式为post
	 * 请求地址为localhost:8080/clinic/member/checkrealname
	 * 返回一个只有一个key为valid，值为true或false的hashmap，true说明请求输入进来的realname可用
	 * 否则则说明请求输入进来的realname不可用
	 */
	@PostMapping
	@RequestMapping("checkrealname")
	public HashMap<String, Boolean> checkRealname(String realname) {
		HashMap<String,Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("valid", memberService.checkMemberRealname(realname));
		return hashMap;
	}
	
	/*
	 * 根据前端前来的新增成员信息封装成成员对象来添加成员
	 * 请求方式为post
	 * 请求地址为localhost:8080/clinic/member/addmember
	 * 需要‘admin:add’的权限
	 */
	@PostMapping
	@RequestMapping("addmember")
	@RequiresPermissions("admin:add")
	public Integer addMember(@RequestBody Member member) {
		return memberService.addMember(member);
	}
	
	/*
	 * 根据前端前来的新修改成员信息封装成成员对象来修改成员
	 * 请求方式为post
	 * 请求地址为localhost:8080/clinic/member/editmember
	 * 需要‘admin:update’的权限
	 * 返回1则说明修改成功
	 * 返回0则说明修改失败
	 */
	@PostMapping
	@RequestMapping("editmember")
	@RequiresPermissions("admin:update")
	public Integer editMember(@RequestBody Member member) {
		return memberService.editeMember(member);
	}
	
	/*
	 * 根据前端前来的成员ID删除成员
	 * 请求方式为get
	 * 请求地址为localhost:8080/clinic/member/delmember/成员id
	 * 需要‘admin:delete’的权限
	 * 返回1则说明删除成功
	 * 返回0则说明删除失败
	 */
	@GetMapping
	@RequestMapping("delmember/{id}")
	@RequiresPermissions("admin:delete")
	public Integer delMember(@PathVariable("id") Integer id) {
		return memberService.delMemberById(id);
	}
}
