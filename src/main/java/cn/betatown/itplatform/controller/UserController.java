package cn.betatown.itplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.betatown.itplatform.model.User;
import cn.betatown.itplatform.service.user.UserService;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/add", produces = { "application/json;charset=UTF-8" })
	public int addUser(User user) {
		return userService.addUser(user);
	}

	@ResponseBody
	@RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = { "application/json;charset=UTF-8" })
	public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
		return userService.findAllUser(pageNum, pageSize);
	}
}
