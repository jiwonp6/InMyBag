package com.lec.mybag.dto;

import java.sql.Timestamp;

public class FaqBoardDto {
	private int fId;
	private String aId;
	private String fTitle;
	private String fContent;
	private String fFilename;
	private int fHit;
	private Timestamp fRdate;
	private String fIp;
	public FaqBoardDto() {	}
	public FaqBoardDto(int fId, String aId, String fTitle, String fContent, String fFilename, int fHit,
			Timestamp fRdate, String fIp) {
		this.fId = fId;
		this.aId = aId;
		this.fTitle = fTitle;
		this.fContent = fContent;
		this.fFilename = fFilename;
		this.fHit = fHit;
		this.fRdate = fRdate;
		this.fIp = fIp;
	}
	public int getfId() {
		return fId;
	}
	public void setfId(int fId) {
		this.fId = fId;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getfTitle() {
		return fTitle;
	}
	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}
	public String getfContent() {
		return fContent;
	}
	public void setfContent(String fContent) {
		this.fContent = fContent;
	}
	public String getfFilename() {
		return fFilename;
	}
	public void setfFilename(String fFilename) {
		this.fFilename = fFilename;
	}
	public int getfHit() {
		return fHit;
	}
	public void setfHit(int fHit) {
		this.fHit = fHit;
	}
	public Timestamp getfRdate() {
		return fRdate;
	}
	public void setfRdate(Timestamp fRdate) {
		this.fRdate = fRdate;
	}
	public String getfIp() {
		return fIp;
	}
	public void setfIp(String fIp) {
		this.fIp = fIp;
	}
	@Override
	public String toString() {
		return "FaqBoardDto [fId=" + fId + ", aId=" + aId + ", fTitle=" + fTitle + ", fContent=" + fContent
				+ ", fFilename=" + fFilename + ", fHit=" + fHit + ", fRdate=" + fRdate + ", fIp=" + fIp + "]";
	}
	
}
