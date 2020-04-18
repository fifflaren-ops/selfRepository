package cn.jj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
	@PostMapping
	@RequestMapping("login")
	public String login(String username,String password) {
		System.out.println(username);
		System.out.println(password);
		return null;
	}
}
