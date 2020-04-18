package cn.jj.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import cn.jj.pojo.Member;;

public interface MemberMapper {
	List<Member>memberList(@Param("keyword") String keyword);
	Member checkMemberUsername(@Param("username")String username);
	Member checkMemberRealname(@Param("realname")String realname);
	Integer addMember(@Param("member")Member member);
	Member getMemberById(Integer id);
	Integer editMember(@Param("member") Member member);
	Integer delMemberById(Integer id);
	Member login(@Param("username")String username,@Param("password")String password);
}
