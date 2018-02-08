package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
	public String loginform() {
		
		return "user/loginform";
	}
	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public String login(@RequestParam("email") String email, 
						@RequestParam("password") String password,
						HttpSession session) {
	
		UserVo authUser = userService.login(email, password);
		if(authUser != null) {
			session.setAttribute("authUser", authUser);
				return "/main/index";
		}else {
						
			return "redirect:/user/loginform?result=fail";
		}
	}
	@RequestMapping(value = "/joinform", method = RequestMethod.GET)
	public String joinform() {
		
		return "user/joinform";
	}
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(UserVo userVo) {
		
		userService.join(userVo);
		System.out.println(userVo.toString());
		return "user/joinsuccess";
		
	}
	
	@RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
	public String joinsuccess(UserVo userVo) {
		
		userService.join(userVo);
		System.out.println(userVo.toString());
		return "user/joinsuccess";
		
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(UserVo userVo,HttpSession session ) {
		session.invalidate();
		return "user/loginform";
	}
	@RequestMapping(value = "/modifyform", method = RequestMethod.GET)
	public String modifyform(Model model, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		return "user/modifyform";
	}
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		
		UserVo authUser =  (UserVo) session.getAttribute("authUser");
		userService.modify(authUser, userVo);
		authUser.setName(userVo.getName());
		
		return "redirect:/main";
		
		
		
	}
	
	
}