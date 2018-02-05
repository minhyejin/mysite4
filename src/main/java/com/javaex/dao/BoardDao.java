package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlsession;
	
	public List<BoardVo> boardList(){
		
		List<BoardVo >bList = sqlsession.selectList("board.selectList");
		return bList;
		
	}
	public BoardVo getArticle(int no) {
		
		 return sqlsession.selectOne("board.getArticle", no);

	}
	public void updateHit(int no) {
		sqlsession.update("board.view", no);
		System.out.println("hit 완료");
	
	}
	
	public void write(BoardVo boardVo) {
		sqlsession.insert("board.insert", boardVo);
		System.out.println("write 완료");
		
	}
}