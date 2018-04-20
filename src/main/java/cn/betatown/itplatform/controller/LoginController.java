package cn.betatown.itplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	//http://localhost:8080/login
	@RequestMapping("/login")
	public String toIndex() {
		System.out.println("to login jsp");
		return "login";
	}

}
