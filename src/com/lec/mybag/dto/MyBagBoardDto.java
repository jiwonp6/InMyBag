package com.lec.mybag.dto;

import java.sql.Timestamp;

public class MyBagBoardDto {
	private int bId;
	private String mId;
	private String bName;
	private String bContent;
	private String bFilename;
	private int bHit;
	private Timestamp bRdate;
	private String bIp;
	public MyBagBoardDto() {	}
	public MyBagBoardDto(int bId, String mId, String bName, String bContent, String bFilename, int bHit,
			Timestamp bRdate, String bIp) {
		super();
		this.bId = bId;
		this.mId = mId;
		this.bName = bName;
		this.bContent = bContent;
		this.bFilename = bFilename;
		this.bHit = bHit;
		this.bRdate = bRdate;
		this.bIp = bIp;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public String getbFilename() {
		return bFilename;
	}
	public void setbFilename(String bFilename) {
		this.bFilename = bFilename;
	}
	public int getbHit() {
		return bHit;
	}
	public void setbHit(int bHit) {
		this.bHit = bHit;
	}
	public Timestamp getbRdate() {
		return bRdate;
	}
	public void setbRdate(Timestamp bRdate) {
		this.bRdate = bRdate;
	}
	public String getbIp() {
		return bIp;
	}
	public void setbIp(String bIp) {
		this.bIp = bIp;
	}
	@Override
	public String toString() {
		return "MyBagBoardDto [bId=" + bId + ", mId=" + mId + ", bName=" + bName + ", bContent=" + bContent
				+ ", bFilename=" + bFilename + ", bHit=" + bHit + ", bRdate=" + bRdate + ", bIp=" + bIp + "]";
	}
	
}
