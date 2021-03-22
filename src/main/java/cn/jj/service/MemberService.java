package cn.jj.service;

import java.util.List;

import cn.jj.pojo.Member;

public interface MemberService {
	
	//模糊搜索关键字返回成员列表
	List<Member>personsList(String keyword);
	
	//根据用户名查询用户是否存在
	Boolean checkMemberUsername(String username);
	
	//根据用户真实姓名查询用户姓名是否存在
	Boolean checkMemberRealname(String realname);
	
	//根据前端提交的成员信息封装成成员对象添加成员
	Integer addMember(Member member);
	
	//根据成员ID获取成员
	Member getMemberById(Integer id);
	
	//根据前端提交的成员信息封装成成员对象编辑成员
	Integer editeMember(Member member);
	
	//根据成员ID删除成员
	Integer delMemberById(Integer id);
	
	//根据用户名获取成员对象，获取的对象用于shiro的密码验证逻辑
	Member login(String username);
}
