package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.FileUploadVo;

@Repository
public class FileUploadDao {
	@Autowired
	private SqlSession sqlsession;
	
	public void insert(FileUploadVo fileuploadVo) {
		System.out.println(fileuploadVo.toString());
		sqlsession.insert("fileupload.insert",fileuploadVo);
		System.out.println("insert 완료 ");
	}
	public List<FileUploadVo> getList(){
		List<FileUploadVo> fList = sqlsession.selectList("fileupload.getList");
		return fList;
	}
	public void delete(int no ) {
		sqlsession.delete("fileupload.delete",no);
		
	}
}
