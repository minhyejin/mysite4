package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guestbook")
public class GuestbookController {
	@Autowired//자동으로 연결해달라 
	private GuestbookService guestbookService;

	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		System.out.println("list 진입");
		
		List<GuestbookVo> gList = guestbookService.getList();
		model.addAttribute("gList", gList) ;
		
		return "guestbook/list";
	}
	

	@RequestMapping(value = "/deleteform", method = RequestMethod.GET)
	public String deleteform(@RequestParam("no") int no, Model model) {
		System.out.println("deleteform 진입");
		
		model.addAttribute("no", no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("no") int no, 
						 @RequestParam("password") String password,
						 GuestbookVo guestbookVo) {
		System.out.println("delete 진입");
		guestbookService.delete(no, password);
		System.out.println(guestbookVo.toString());
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println(guestbookVo.toString());
		
		guestbookService.add(guestbookVo);
		
		return "redirect:/guestbook/list";
	}
	

}
