package cn.jj.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//登录控制器
@Controller
public class UserController {
	@PostMapping
	@RequestMapping("login")
	public String login(String username,String password,Boolean rememberMe,Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
		if (rememberMe == null) {
			rememberMe = false;
		}
		usernamePasswordToken.setRememberMe(rememberMe);
		try {
			subject.login(usernamePasswordToken);
		} catch (UnknownAccountException unknownAccountException) {
			model.addAttribute("msg", "用户名不存在");
			return "login";
		}catch (IncorrectCredentialsException incorrectCredentialsException) {
			model.addAttribute("msg", "密码错误");
			return "login";
		}
		System.out.println("验证成功");
		return "index";
	}
}
