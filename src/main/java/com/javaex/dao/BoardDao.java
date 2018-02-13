package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlsession;
	
	
	public List<BoardVo> boardList(int startRnum, int endRnum, String kwd){
		Map<String, Object> mapCri= new HashMap<String, Object>();
		mapCri.put("startRnum", startRnum);
		mapCri.put("endRnum", endRnum);
		mapCri.put("kwd",kwd);
		System.out.println("dao:" +mapCri.toString());
		return sqlsession.selectList("board.selectList",mapCri);	
	}
	
	public int selectTotalCount(String kwd) {

		return sqlsession.selectOne("board.totalCount", kwd);
	
	}
	 
	public BoardVo getArticle(int no) {
		
		 return sqlsession.selectOne("board.getArticle", no);

	}
	public void updateHit(int no) {
		sqlsession.update("board.view", no);
	
	}
	
	public void write(BoardVo boardVo) {
		sqlsession.insert("board.insert", boardVo);
		System.out.println("write 완료");
		
	}
	public void delete(int no) {
		sqlsession.delete("board.delete", no);
		System.out.println("delete완료");
		
	}
	public void modify(BoardVo boardVo) {
		sqlsession.update("board.modify", boardVo);
		System.out.println("modify 완료");
	}
	public List<BoardVo> search(String kwd) {
		return sqlsession.selectList("board.selectsearch","%" + kwd + "%");
		
	}

	public List<BoardVo> selectBoardList() {
		
		return null;
	}
}
