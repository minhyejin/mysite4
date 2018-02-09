package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUploadService;



@Controller
@RequestMapping(value = "/fileupload")
public class FileUploadController {

	@Autowired
	private FileUploadService fileuploadService;
	@RequestMapping("/form")//get post둘다 받고싶으면 안써도됨
	public String form(Model model) {
		String url = "upload/";
		
		List l = fileuploadService.getList();
		model.addAttribute("fList", l);
		model.addAttribute("url",url);
		
		return"fileupload/form";
	}
	@RequestMapping("/upload")
	public String upload(@RequestParam("file")MultipartFile file , Model model) {//file을 받을 수 있는 자료형 
		System.out.println(file.toString());
		String savename = fileuploadService.restore(file);//파일 이름만 가져온거 
		 String url = "upload/"+savename;//파일 이름에 붙여준거 
		 System.out.println(url);
		 model.addAttribute("url",url);
		return  "fileupload/result";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam ("no")int no) {
		fileuploadService.delete(no);
		return "redirect:/fileupload/form";
	}
	
}
