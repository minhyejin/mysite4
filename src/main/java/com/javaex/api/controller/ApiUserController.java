package com.javaex.api.controller;
//비동기 방식으로 데이터 전송하는 것들만 모아놓음 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;


@Controller
public class ApiUserController {

	@Autowired
	private UserService userService;

	@ResponseBody // body안에 내가 준 데이터를 넣어서 보내라
	@RequestMapping(value = "/user/api/emailcheck", method = RequestMethod.POST)
	public boolean emailcheck(@RequestBody UserVo userVo) {// dispatcherServlet이 주는거
		System.out.println(userVo);
		/*boolean result = userService.getUser(userVo);*/
/*
		System.out.println(result);*/
		return userService.getUser(userVo.getEmail());
	}

	/*@ResponseBody // body안에 내가 준 데이터를 넣어서 보내라
	@RequestMapping(value = "/user/api/jasonTest", method = RequestMethod.GET)
	public UserVo emailcheckTest() {
		UserVo userVo = userService.getUser(7);
		System.out.println(userVo);
		return userVo;
	}*/
}
