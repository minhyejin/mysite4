package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public UserVo login(String email, String password) {
		
		return userDao.getUser(email, password);
		
	}
	public void join(UserVo userVo) {
		
		 userDao.insert(userVo);
	}

	public void modify(UserVo authUser , UserVo newAuthUser ) {
		if(authUser.getNo() == newAuthUser.getNo()) {
			UserVo userVo = userDao.getUser(authUser.getNo());
			userVo.setName(newAuthUser.getName());
			if(newAuthUser.getPassword()!="")
				userVo.setPassword(newAuthUser.getPassword());
			userVo.setGender(newAuthUser.getGender());
			userDao.update(userVo);
		}
		
	}
	public UserVo getUser(int no) {
		return userDao.getUser(no);
	}
}
