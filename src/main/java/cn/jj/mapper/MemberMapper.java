package cn.jj.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import cn.jj.pojo.Member;;

public interface MemberMapper {
	
	//模糊搜索关键字返回成员列表
	List<Member>memberList(@Param("keyword") String keyword);
	
	//根据用户名查询用户是否存在
	Member checkMemberUsername(@Param("username")String username);
	
	//根据用户真实姓名查询用户姓名是否存在
	Member checkMemberRealname(@Param("realname")String realname);
	
	//根据前端提交的成员信息封装成成员对象添加成员
	Integer addMember(@Param("member")Member member);
	
	//根据成员ID获取成员
	Member getMemberById(Integer id);
	
	//根据前端提交的成员信息封装成成员对象编辑成员
	Integer editMember(@Param("member") Member member);
	
	//根据成员ID删除成员
	Integer delMemberById(Integer id);
	
	//根据用户名获取成员对象，获取的对象用于shiro的密码验证逻辑
	Member login(@Param("username")String username);
}
