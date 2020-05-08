package cn.jj.configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfiguration {
	@Bean(name = "shiroFilterFactoryBean")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier(value = "defaultSecurityManager")SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		LinkedHashMap<String,Filter> filtersMap = new LinkedHashMap<String, Filter>();
		filtersMap.put("logout", getMyShiroLogoutFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);
		shiroFilterFactoryBean.setLoginUrl("tologin");
		shiroFilterFactoryBean.setSuccessUrl("index");
		shiroFilterFactoryBean.setUnauthorizedUrl("unauthorized");
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
		filterChainDefinitionMap.put("/unauthorized", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/**", "user");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	@Bean(name = "defaultSecurityManager")
	public SecurityManager getSecurityManager(@Qualifier(value = "myRealm")MyRealm myRealm) {
		DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
		//设置自定义授权认证
		defaultSecurityManager.setRealm(myRealm);
		//设置自定义记住我管理器
		defaultSecurityManager.setRememberMeManager(rememberMeManager());
		//设置缓存管理器用redis进行缓存处理
		defaultSecurityManager.setCacheManager(cacheManager());
		//设置session管理器
		defaultSecurityManager.setSessionManager(sessionManager());
		return defaultSecurityManager;
	}
	@Bean(name = "myRealm")
	public MyRealm  getMyRealm() {
		MyRealm myRealm = new MyRealm();
		//缓存realm信息
		myRealm.setCachingEnabled(true);
		//开启缓存权限信息的缓存
		myRealm.setAuthorizationCachingEnabled(true);
		//缓存的名字
		myRealm.setAuthorizationCacheName("authorizationCache");
		return myRealm;
	}
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	public MyShiroLogoutFilter getMyShiroLogoutFilter() {
		MyShiroLogoutFilter myShiroLogoutFilter = new MyShiroLogoutFilter();
		myShiroLogoutFilter.setRedirectUrl("tologin");
		return myShiroLogoutFilter;
	}
	/**
	 * 配置shiro在thymeleaf中使用shiro标签
	 * @return
	 */
	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}
	/**
	    *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	    * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
	    * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
	    DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
	    advisorAutoProxyCreator.setProxyTargetClass(true);
	    return advisorAutoProxyCreator;
	}
	/**
	    * 开启aop注解支持
	    * @param securityManager
	    * @return
	*/
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	/**
	 * 解决： 无权限页面不跳转 shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized") 无效
	 * shiro的源代码ShiroFilterFactoryBean.Java定义的filter必须满足filter instanceof AuthorizationFilter，
	 * 只有perms，roles，ssl，rest，port才是属于AuthorizationFilter，而anon，authcBasic，auchc，user是AuthenticationFilter，
	 * 所以unauthorizedUrl设置后页面不跳转 Shiro注解模式下，登录失败与没有权限都是通过抛出异常。
	 * 并且默认并没有去处理或者捕获这些异常。在SpringMVC下需要配置捕获相应异常来通知用户信息
	 * @return
	 */
	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
	    SimpleMappingExceptionResolver simpleMappingExceptionResolver=new SimpleMappingExceptionResolver();
	    Properties properties=new Properties();
	    //这里的 /unauthorized 是页面，不是访问的路径
	    properties.setProperty("org.apache.shiro.authz.UnauthorizedException","unauthorized");
	    properties.setProperty("org.apache.shiro.authz.UnauthenticatedException","unauthorized");
	    properties.setProperty("org.apache.shiro.authz.AuthorizationException","unauthorized");
	    simpleMappingExceptionResolver.setExceptionMappings(properties);
	    
	    return simpleMappingExceptionResolver;
	}
	/**
	 * 表单认证过滤器，处理前段<input type='checkbox' name='rememberMe'/>
	 */
	@Bean
	public FormAuthenticationFilter formAuthenticationFilter() {
		FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
		formAuthenticationFilter.setRememberMeParam("rememberMe");
		return formAuthenticationFilter;
	}
	/**
	 * 
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
		return cookieRememberMeManager;
	}
	@Bean
	public SimpleCookie rememberMeCookie() {
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		simpleCookie.setHttpOnly(true);
		simpleCookie.setPath("/");
		simpleCookie.setMaxAge(86400);
		return simpleCookie;
	}
	/**
	 * 让某个实例的某个方法的返回值注入为Bean的实例
	 * @param myRealm
	 * @return
	 */
	@Bean
	public MethodInvokingFactoryBean getMethodInvokingFactoryBean(@Qualifier("myRealm")MyRealm myRealm) {
		MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
		methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
		methodInvokingFactoryBean.setArguments(new Object[] {getSecurityManager(myRealm)});
		return methodInvokingFactoryBean;
	}
	/**
	 * 配置session监听
	 * @return
	 */
	@Bean
	public ShiroSessionListener shiroSessionListener() {
		return new ShiroSessionListener();
	}
	/**
	 * 配置sessionId生成器
	 * @return
	 */
	@Bean
	public SessionIdGenerator sessionIdGenerator() {
		return new JavaUuidSessionIdGenerator();
	}
	/**
	 *  SessionDAO的作用是为Session提供CRUD并进行持久化的一个shiro组件
	 * MemorySessionDAO 直接在内存中进行会话维护
	 * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
	 * @return
	 */
	@Bean
	public SessionDAO sessionDAO() {
		EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
		//配置redis的缓存管理器
		//enterpriseCacheSessionDAO.setCacheManager(cacheManager);
		enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiroSessionDao");
		enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator());
		return enterpriseCacheSessionDAO;
	}
	/**
	 * 配置用于保存sessionId的cookie
	 * @return
	 */
	@Bean
	public SimpleCookie sessionIdCookie() {
		SimpleCookie simpleCookie = new SimpleCookie("sessionId");
		simpleCookie.setHttpOnly(true);
		simpleCookie.setPath("/");
		//设置-1当关闭浏览器时就清楚该sessionId的cookie
		simpleCookie.setMaxAge(-1);
		return simpleCookie;
	}
	@Bean(name = "sessionManager")
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		//设置redis的cachemanager
		sessionManager.setCacheManager(cacheManager());
		//sessionManager.setCacheManager(cacheManager);
		ArrayList<SessionListener> sessionListenerList = new ArrayList<SessionListener>();
		sessionListenerList.add(shiroSessionListener());
		sessionManager.setSessionListeners(sessionListenerList);
		sessionManager.setSessionDAO(sessionDAO());
		sessionManager.setSessionIdCookie(sessionIdCookie());
		//设置session的超时时间，单位是毫秒
		sessionManager.setGlobalSessionTimeout(1800000);
		//设置URL上面不带有sessionId
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		//删除无效的session
		sessionManager.setDeleteInvalidSessions(true);
		//设置自动检测过期session
		sessionManager.setSessionValidationSchedulerEnabled(true);
		//设置session的有效时间，单位为毫秒，如果用户直接关闭浏览器而不退出登录的话，session就会一直存在，占地方，要设置一个session的有效时间
		sessionManager.setSessionValidationInterval(3600000);
		return sessionManager;
	}
	/**
	 * shiro-redis管理器
	 */
	@Bean
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost("192.168.92.121");
		redisManager.setPort(6379);
		redisManager.setPassword("123456");
		return redisManager;
	}
	/**
	 * 缓存管理器使用redis的缓存管理器
	 */
	@Bean
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		//使用用户名的username作为id
		redisCacheManager.setPrincipalIdFieldName("username");
		//设置缓存时间 权限
		redisCacheManager.setExpire(200000);
		return redisCacheManager;
	} 
}
