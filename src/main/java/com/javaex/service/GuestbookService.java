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

	public List<GuestbookVo> getGuestbookPage(int page) {
		return guestbookDao.selectGuestbookPage(page);
	}

	public GuestbookVo insert(GuestbookVo guestbookVo) {
		guestbookDao.insertajax(guestbookVo);//쓴글을 더하고 시퀀스 번호를 받는다 
		String sysdate = guestbookDao.sysdate(guestbookVo);//다오를 sysdate에 저장
		guestbookVo.setRegDate(sysdate);//시퀀스로 guestbookVo에 받은 번호로 sysdate를 지정 
		return guestbookVo; 
	}

	

	
	
}
