package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlsession;
	public UserVo getUser(String email, String password) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		return sqlsession.selectOne("user.selectUserByEmailPw",map);
	}
	public void insert(UserVo userVo) {
	
		sqlsession.insert("user.insert", userVo);
		
	}
	public void update(UserVo userVo) {
		
		 sqlsession.update("user.update", userVo);
	}

	public UserVo getUser(int no) {
		System.out.println(no);
		return sqlsession.selectOne("user.selectUserByNo", no);//고쳐야함
		
	}
	
	public UserVo getUser(String email) {
	 
		return sqlsession.selectOne("user.selectUserByEmail",email);
	}
}
