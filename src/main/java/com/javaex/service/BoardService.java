package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	
	public Map<String,Object> boardList(int crtPage, String kwd) {
		//게시글 리스트 
		int listCnt = 10;
		/*int crtPage = 1;*/
		if(crtPage<0) {
			crtPage = 1;
		}
		
		int startRnum = (crtPage-1)*listCnt; //0부터 10, 20
		int endRnum = startRnum + listCnt;//10, 20, 30-
		System.out.println("startRnum:" + startRnum);
		System.out.println("endRnum:" + endRnum);
		List<BoardVo> boardList =  boardDao.boardList(startRnum, endRnum, kwd);
		//전체 페이지 글 개수
		
		int totalCount = boardDao.selectTotalCount(kwd);
		System.out.println("totalCount:" + totalCount);
		//페이지 버튼작업 
		int pageBtnCount = 5;
		int endPageBtnNo = (int)(Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount);//올림 
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
		boolean next = false;
		if(endPageBtnNo * listCnt <totalCount) {
			next = true;
		}else {
			endPageBtnNo = (int)(Math.ceil(totalCount/(double)listCnt));
			System.out.println("endPageBtnNo"+endPageBtnNo);
		}
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("boardList", boardList);
		bMap.put("prev", prev);
		bMap.put("startPageBtnNo", startPageBtnNo);
		bMap.put("endPageBtnNo", endPageBtnNo);
		bMap.put("next", next);
		bMap.put("crtPage",crtPage);
		bMap.put("kwd",kwd);
		return bMap;

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
	public void modify(BoardVo boardVo) {
		boardDao.modify(boardVo);
	}
	public List<BoardVo> search(String kwd) {
		return boardDao.search(kwd);
		
		
	}public List<BoardVo> getBoardList(){
		int listCnt = 10;
		int crtPage = 1;
		int startRnum = (crtPage-1)*listCnt; //0부터 10, 20
		int endRnum = startRnum + listCnt;//10, 20, 30-
		System.out.println("startRnum:" + startRnum);
		System.out.println("endRnum:" + endRnum);
 		return boardDao.selectBoardList();
	}
}
