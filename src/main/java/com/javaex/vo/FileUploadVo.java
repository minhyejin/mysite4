package com.javaex.vo;

public class FileUploadVo {
	int no;
	String savename;
	long fileSize;
	String filePath;
	String exname;
	String orgname;

	public FileUploadVo() {
	}
	public FileUploadVo(int no, String savename, long fileSize, String filePath, String exname, String orgname) {
		this.no = no;
		this.savename = savename;
		this.fileSize = fileSize;
		this.filePath = filePath;
		this.exname = exname;
		this.orgname = orgname;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSavename() {
		return savename;
	}


	public void setSavename(String savename) {
		this.savename = savename;
	}


	public long getFileSize() {
		return fileSize;
	}


	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getExname() {
		return exname;
	}


	public void setExname(String exname) {
		this.exname = exname;
	}


	public String getOrgname() {
		return orgname;
	}


	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}


	@Override
	public String toString() {
		return "FileUploadVo [no=" + no + ", savename=" + savename + ", fileSize=" + fileSize + ", filePath=" + filePath
				+ ", exname=" + exname + ", orgname=" + orgname + "]";
	}
	
	
	
}
