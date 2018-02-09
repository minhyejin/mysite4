package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileUploadDao;
import com.javaex.vo.FileUploadVo;

@Service

public class FileUploadService {
	@Autowired
	private FileUploadDao fileuploadDao;
	
	public String restore(MultipartFile file) {
		
		String saveDir = "D:\\javastudy\\upload";
		//파일 정보 수집
		 FileUploadVo fileuploadVo = new FileUploadVo ();
		//원래 파일 이름
		String orgname = file.getOriginalFilename();
		System.out.println(orgname);
		//확장자
		String exname = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));//.으로 구분해서 잘라줘라 
		System.out.println(exname);
		//저장파일 이름
		String savename = System.currentTimeMillis() + UUID.randomUUID().toString()+exname;//현재시간을 기준으로 랜덤하게 찍어주는 숫자 (같을 수가 없음)
		System.out.println(savename);
		//저장 위치{패스}
		String filePath = saveDir + "\\" + savename;
		System.out.println(filePath);
		//파일 사이즈
		long fileSize = file.getSize();
		System.out.println(fileSize);
		//vo 만들어서 dao통해서 데이터베이스에 저장
		
		//파일 복사
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(saveDir +"/"+savename);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			bout.write(fileData);
			
			if(bout !=null) {
				bout.close();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	
		fileuploadVo.setExname(exname);
		fileuploadVo.setOrgname(orgname);
		fileuploadVo.setFilePath(filePath);
		fileuploadVo.setSavename(savename);
		fileuploadVo.setFileSize(fileSize);
		System.out.println(fileuploadVo.toString());
		fileuploadDao.insert(fileuploadVo);		
		return savename;	
	}

	public List<FileUploadVo> getList() {
		List<FileUploadVo> fList = fileuploadDao.getList();
		System.out.println("list 출력"+fList.toString());
		return fList;
	}
	public void delete(int no) {
		fileuploadDao.delete(no);
	}
}
