package cn.jj.configuration;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class ShiroSessionListener implements SessionListener{
	
	/**
	 * 统计在线人数
	 * juc包下线程安全自增
	 */
	
	private final AtomicInteger sessionCount = new AtomicInteger();
	/**
	 * 会话创建时触发
	 */
	@Override
	public void onStart(Session session) {
		//会话被创建就+1
		sessionCount.incrementAndGet();
	}
	/**
	 * 退出会话时触发
	 */
	@Override
	public void onStop(Session session) {
		//会话退出-1
		sessionCount.decrementAndGet();
	}
	/**
	 * 会话超时就减一
	 */
	public void onExpiration(Session session) {
		//会话退出-1
		sessionCount.decrementAndGet();
	}
	/**
	 * 获取sessionCount在线人数
	 */
	public AtomicInteger getAtomicInteger() {
		return this.sessionCount;
	}
}
