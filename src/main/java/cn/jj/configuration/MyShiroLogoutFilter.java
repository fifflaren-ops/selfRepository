package cn.jj.configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
//自定义退出过滤器
public class MyShiroLogoutFilter extends LogoutFilter{

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		//登出操作 清除缓存 subject.logout() 可以自动清理缓存信息, 这些代码是可以省略的 这里只是做个笔记 表示这种方式也可以清除
		Subject subject = getSubject(request,response);
		//登出
		subject.logout();
		//获取登出后重定向到的地址
		String redirectUrl = getRedirectUrl(request,response,subject);
		//重定向
		issueRedirect(request,response,redirectUrl);
		return false;
	}
}
