package cn.jj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@PostMapping
	@RequestMapping(value = "list",consumes = "application/json")
	public PageInfo<Member> doctorsList(@RequestBody Map<String, Object> map){
		Integer pageNum = (Integer) map.get("pageNum");
		Integer pageSize = (Integer) map.get("pageSize");
		String keyword = (String) map.get("keyword");
		System.out.println(keyword);
		PageHelper.startPage(pageNum, pageSize);
		List<Member> personsList = memberService.personsList(keyword);
		PageInfo<Member> pageInfo = new PageInfo<Member>(personsList);
		return pageInfo;
	}
	@PostMapping
	@RequestMapping("checkusername")
	public HashMap<String, Boolean> checkUsername(String username) {
		HashMap<String,Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("valid", memberService.checkMemberUsername(username));
		return hashMap;
	}
	@PostMapping
	@RequestMapping("checkrealname")
	public HashMap<String, Boolean> checkRealname(String realname) {
		HashMap<String,Boolean> hashMap = new HashMap<String, Boolean>();
		hashMap.put("valid", memberService.checkMemberRealname(realname));
		return hashMap;
	}
	@PostMapping
	@RequestMapping("addmember")
	public Integer addMember(@RequestBody Member member) {
		return memberService.addMember(member);
	}
	@PostMapping
	@RequestMapping("editmember")
	public Integer editMember(@RequestBody Member member) {
		return memberService.editeMember(member);
	}
	@GetMapping
	@RequestMapping("delmember/{id}")
	public Integer delMember(@PathVariable("id") Integer id) {
		return memberService.delMemberById(id);
	}
}
