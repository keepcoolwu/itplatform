package cn.betatown.itplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	//http://localhost:8080/login
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelMap model = new ModelMap();
		model.addAttribute("name", "Spring Boot");
		return new ModelAndView("login", model);
	}

}
