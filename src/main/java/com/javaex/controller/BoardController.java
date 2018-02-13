package com.javaex.controller;

import java.util.List;
import java.util.Map;

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
	public String list(@RequestParam(value = "crtPage", required = false, defaultValue ="1") Integer crtPage ,
			@RequestParam(value = "kwd", required = false, defaultValue ="") String kwd,			
			Model model) {
	Map<String,Object> bMap = boardService.boardList(crtPage, kwd);
	model.addAttribute("bMap", bMap);
	return "board/list";	
}
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String getArticle(@RequestParam("no") int no , Model model,
			@RequestParam(value = "crtPage", required = false, defaultValue ="1") Integer crtPage ) {
		BoardVo view = boardService.view(no);
		model.addAttribute("boardVo",view);
		BoardVo boardVo = boardService.getArticle(no);
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("no", no);
		model.addAttribute("crtPage", crtPage);
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
		boardVo.setUserNo(authUser.getNo());
		boardVo.setWriter(authUser.getName());
		if (authUser != null) {
			boardService.write(boardVo);
		}
		return "redirect:/board/list";
	}
	@RequestMapping(value ="/delete", method = RequestMethod.GET)
	public String delete(HttpSession session, @RequestParam("no") int no, @RequestParam("userNo") int userNo  ) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if ((authUser != null) && (authUser.getNo() == userNo)) {
		boardService.delete(no);	
		}
		return "redirect:/board/list";
	}
	
	@RequestMapping(value ="/modifyform", method = RequestMethod.GET)
	public String modifyform(HttpSession session, Model model, @RequestParam("no") int no) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if ((authUser != null)){//로그인성공
			BoardVo boardVo = boardService.getArticle(no);
			model.addAttribute("boardVo", boardVo);
			model.addAttribute("no", no);
	
			return "board/modify";
		}else {
			return "redirect:/board/list";
		}	
	}
	@RequestMapping(value ="/modify", method = RequestMethod.GET)
	public String modify(HttpSession session,BoardVo boardVo) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if ((authUser != null)) {
			boardService.modify(boardVo);
		}
			return "redirect:/board/list";
	}
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search( @RequestParam String kwd, Model model) {
		List<BoardVo> bList = boardService.search(kwd);
		model.addAttribute("bList", bList);
		return "board/list";
	}
	
	
}