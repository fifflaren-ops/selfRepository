package cn.jj.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfiguration {
	@Bean(name = "shiroFilterFactoryBean")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier(value = "defaultSecurityManager")SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("tologin");
		shiroFilterFactoryBean.setSuccessUrl("index");
		/**
		 * anon 无需认证
		 * authc 需要登录
		 * user 记住我登录
		 * perms 需要权限
		 * role 需要角色
		 */
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/tologin", "anon");
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	@Bean(name = "defaultSecurityManager")
	public SecurityManager getSecurityManager(@Qualifier(value = "myRealm")MyRealm myRealm) {
		DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
		defaultSecurityManager.setRealm(myRealm);
		return defaultSecurityManager;
	}
	@Bean(name = "myRealm")
	public MyRealm  getMyRealm() {
		return new MyRealm();
	}
	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}
}
