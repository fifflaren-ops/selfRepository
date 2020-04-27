package cn.jj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jj.mapper.MemberMapper;
import cn.jj.pojo.Member;
import cn.jj.service.MemberService;
@Service
@Transactional
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberMapper memberMapper;
	@Override
	public List<Member> personsList(String keyword) {
		return memberMapper.memberList(keyword);
	}
	@Override
	public Boolean checkMemberUsername(String username) {
		return memberMapper.checkMemberUsername(username)==null?true:false;
	}
	@Override
	public Boolean checkMemberRealname(String realname) {
		return memberMapper.checkMemberRealname(realname)==null?true:false;
	}
	@Override
	public Member getMemberById(Integer id) {
		return memberMapper.getMemberById(id);
	}
	@Override
	public Integer editeMember(Member member) {
		return memberMapper.editMember(member);
	}
	@Override
	public Integer delMemberById(Integer id) {
		return memberMapper.delMemberById(id);
	}
	@Override
	public Integer addMember(Member member) {
		return memberMapper.addMember(member);
	}
	@Override
	public Member login(String username) {
		return memberMapper.checkMemberUsername(username);
	}
}
