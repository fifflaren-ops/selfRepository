package cn.jj.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jj.pojo.Member;
import cn.jj.pojo.Permission;
import cn.jj.service.MemberService;
import cn.jj.service.PermissionService;
import cn.jj.service.RoleService;
//自定义MyRealm
public class MyRealm extends AuthorizingRealm{
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	/**
	 * 从principals拿出成员
	 * 通过成员的角色获取其权限ID列表
	 * 遍历ID列表获取权限对象
	 * 获取到的权限对象装进一个ArrayList里面
	 * 新建对象SimpleAuthorizationInfo
	 * 把上述的ArrayList赋给SimpleAuthorizationInfo
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Member member = (Member)principals.getPrimaryPrincipal();
		String permissionIdsById = roleService.getPermissionIdsById(member.getRole());
		String[] split = permissionIdsById.split(",");
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (String permissioinId : split) {
			ids.add(Integer.valueOf(permissioinId));
		}
		List<Permission> permissionObjects = permissionService.getPermissionsByIds(ids);
		ArrayList<String> permissions = new ArrayList<String>();
		for (Permission permissionObject : permissionObjects) {
			permissions.add(permissionObject.getExpression());
		}
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}
	/**
	 * 通过AuthenticationToken获取当前用户在登录页面输入的用户和密码所生成的对象
	 * 通过UsernamePasswordToken获取用户名
	 * 调用MemberService的login方法，传入用户名，如果不存在该用户则返回NULL
	 * 调用MemberService的login方法，传入用户名，如果存在，则把该用户对象，用户密码赋给新建的SimpleAuthenticationInfo
	 * Shiro会自动验证请求输入的密码和数据库中的密码是否相同
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)token;
		String username = usernamePasswordToken.getUsername();
		Member member = memberService.login(username);
		if (member==null) {
			return null;
		}
		return new SimpleAuthenticationInfo(member,member.getPassword(),this.getName());
	}
	/**
	 * 清除指定用户的缓存的认证信息
	 */
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}
	/**
	 * 清除指定用户的缓存的授权信息
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
	 * 清除指定的用户的缓存的认证信息和授权信息
	 */
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}
	/**
	 * 清除所有缓存的认证信息
	 */
	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}
	/**
	 * 清除所有缓存的授权信息
	 */
	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}
	/**
	 * 清楚所有缓存的认证信息和授权信息
	 */
	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}
}
