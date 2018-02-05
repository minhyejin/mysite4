package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;
@Service
public class GuestbookService {
	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> gList = guestbookDao.getList();
		return gList;
	}
	
	public void add(GuestbookVo guestbookVo) {
		
		guestbookDao.insert(guestbookVo);
	}
	
	public void delete(int no, String password) {
		guestbookDao.delete(no, password);
		
	}
}
