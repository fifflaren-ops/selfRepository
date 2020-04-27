package cn.jj.configuration;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;

import cn.jj.pojo.Member;
import cn.jj.service.MemberService;

public class MyRealm extends AuthorizingRealm{
	
	@Autowired
	private MemberService memberService;
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行授权");
		return null;
	}
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("执行认证");
		UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)token;
		String username = usernamePasswordToken.getUsername();
		Member member = memberService.login(username);
		if (member==null) {
			return null;
		}
		return new SimpleAuthenticationInfo("",member.getPassword(),"");
	}

}
