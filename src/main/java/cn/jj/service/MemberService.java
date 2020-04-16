package cn.jj.service;

import java.util.List;

import cn.jj.pojo.Member;

public interface MemberService {
	List<Member>personsList(String keyword);
	Boolean checkMemberUsername(String username);
	Boolean checkMemberRealname(String realname);
	Integer addMember(Member member);
	Member getMemberById(Integer id);
	Integer editeMember(Member member);
	Integer delMemberById(Integer id);
}
