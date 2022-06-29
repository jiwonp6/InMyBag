package com.lec.mybag.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class MyBagBoardDto {
	private int bId;
	private String mId;
	private String bName;
	private String bContent;
	private String bFilename1;
	private String bFilename2;
	private String bFilename3;
	private int bHit;
	private Timestamp bRdate;
	private String bIp;
	public MyBagBoardDto() {	}
	public MyBagBoardDto(int bId, String mId, String bName, String bContent, String bFilename1, String bFilename2,
			String bFilename3, int bHit, Timestamp bRdate, String bIp) {
		this.bId = bId;
		this.mId = mId;
		this.bName = bName;
		this.bContent = bContent;
		this.bFilename1 = bFilename1;
		this.bFilename2 = bFilename2;
		this.bFilename3 = bFilename3;
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
	public String getbFilename1() {
		return bFilename1;
	}
	public void setbFilename1(String bFilename1) {
		this.bFilename1 = bFilename1;
	}
	public String getbFilename2() {
		return bFilename2;
	}
	public void setbFilename2(String bFilename2) {
		this.bFilename2 = bFilename2;
	}
	public String getbFilename3() {
		return bFilename3;
	}
	public void setbFilename3(String bFilename3) {
		this.bFilename3 = bFilename3;
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
				+ ", bFilename1=" + bFilename1 + ", bFilename2=" + bFilename2 + ", bFilename3=" + bFilename3 + ", bHit="
				+ bHit + ", bRdate=" + bRdate + ", bIp=" + bIp + "]";
	}
	
}
