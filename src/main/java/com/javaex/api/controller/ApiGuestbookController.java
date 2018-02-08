package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping(value = "/guestbook/api/listajax", method = RequestMethod.POST)
	public List<GuestbookVo> apiList(@RequestParam("page") int page) {
		
		List<GuestbookVo> guestbookList = guestbookService.getGuestbookPage(page);

		return  guestbookList;
	}
	@ResponseBody
	@RequestMapping(value = "/guestbook/api/selectinsert", method = RequestMethod.POST)
	public GuestbookVo selectinsert(@RequestBody GuestbookVo guestbookVo) {
		System.out.println(guestbookVo.toString());
		return guestbookService.insert(guestbookVo);
		
	}
	@ResponseBody
	@RequestMapping(value = "/guestbook/api/delete", method =RequestMethod.POST)
	public int delete(@RequestBody GuestbookVo guestbookVo) {
		int no = guestbookVo.getNo();
		String password = guestbookVo.getPassword();
		guestbookService.delete(no, password);
		return no;
	}
	
	
}
