package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlsession;

	public void insert(GuestbookVo guestbookVo) {
		sqlsession.insert("guestbook.insert", guestbookVo);
	}

	public List<GuestbookVo> getList() {

		List<GuestbookVo> gList = sqlsession.selectList("guestbook.getList");
		return gList;
	}

	public void delete(int no, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("password", password);

		sqlsession.delete("guestbook.delete", map);

	}

	public List<GuestbookVo> selectGuestbookPage(int page) {

		return sqlsession.selectList("guestbook.selectListByPage", page);
	}
	public GuestbookVo insertajax(GuestbookVo guestbookVo) {
		 sqlsession.insert("guestbook.selectinsert", guestbookVo);//0번시퀀스
		int result = guestbookVo.getNo();//방금입력된 번호 
		System.out.println(result);
		return guestbookVo;
		
		
	}public String sysdate(GuestbookVo guestbookVo) {
		return sqlsession.selectOne("guestbook.sysdate");
	}

	

}
