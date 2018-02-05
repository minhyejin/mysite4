package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	
	public List<BoardVo> boardList() {
		List<BoardVo> bList = boardDao.boardList();
		return bList;
		
	}
	public BoardVo getArticle(int no) {
		return boardDao.getArticle(no);
		
	}public BoardVo view(int no) {
		
		BoardVo boardVo = getArticle(no);
		boardDao.updateHit(no);

		return boardVo;
		
	}public void write(BoardVo boardVo) {
		boardDao.write(boardVo);	
	}
	public void delete(int no) {
		boardDao.delete(no);		
	}
	public void modify(BoardVo modifyboardVo) {
		
		BoardVo boardVo = getArticle(modifyboardVo.getNo());
		boardVo.setTitle(modifyboardVo.getTitle());
		String modifycontent = (modifyboardVo.getContent().replace("\r\n", "<br>"));
		boardVo.setContent(modifycontent);
		boardDao.modify(boardVo);
	}
}
