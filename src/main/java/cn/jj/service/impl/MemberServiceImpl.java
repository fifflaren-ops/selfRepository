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
	
	//模糊搜索关键字返回成员列表
	@Override
	public List<Member> personsList(String keyword) {
		return memberMapper.memberList(keyword);
	}
	
	//根据用户名查询用户是否存在
	@Override
	public Boolean checkMemberUsername(String username) {
		return memberMapper.checkMemberUsername(username)==null?true:false;
	}
	
	//根据用户真实姓名查询用户姓名是否存在
	@Override
	public Boolean checkMemberRealname(String realname) {
		return memberMapper.checkMemberRealname(realname)==null?true:false;
	}
	
	//根据成员ID获取成员
	@Override
	public Member getMemberById(Integer id) {
		return memberMapper.getMemberById(id);
	}
	
	//根据前端提交的成员信息封装成成员对象编辑成员
	@Override
	public Integer editeMember(Member member) {
		return memberMapper.editMember(member);
	}
	
	//根据成员ID删除成员
	@Override
	public Integer delMemberById(Integer id) {
		return memberMapper.delMemberById(id);
	}
	
	//根据前端提交的成员信息封装成成员对象添加成员
	@Override
	public Integer addMember(Member member) {
		return memberMapper.addMember(member);
	}
	
	//根据用户名获取成员对象，获取的对象用于shiro的密码验证逻辑
	@Override
	public Member login(String username) {
		return memberMapper.login(username);
	}
}
