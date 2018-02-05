package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value ="/board")
	public class BoardController {
	@Autowired
	private BoardService boardService;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
	List<BoardVo> bList = boardService.boardList();
	model.addAttribute("bList", bList);
	return "board/list";	
}
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String getArticle(@RequestParam("no") int no , Model model) {
		BoardVo view = boardService.view(no);
		model.addAttribute("boardVo",view);
		BoardVo getarticle = boardService.getArticle(no);
		model.addAttribute("boardVo", getarticle);
		model.addAttribute("no", no);

		return "board/view";
	}
	@RequestMapping(value ="/writeform", method = RequestMethod.GET)
	public String writeform(HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			return "redirect:/board/list";	
		}
		return "board/write";
		
	}
	@RequestMapping(value ="/write", method = RequestMethod.GET)
	public String write(HttpSession session, @ModelAttribute BoardVo boardVo) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser != null) {
			boardService.write(boardVo);
		}
		return "redirect:/board/list";
	}
	
	
	
}