package com.lec.mybag.dto;

import java.sql.Timestamp;

public class NoticeBoardDto {
	private int nId;
	private String aId;
	private String nTitle;
	private String nContent;
	private String nFilename;
	private int nHit;
	private Timestamp nRdate;
	private String nIp;
	public NoticeBoardDto() {}
	public NoticeBoardDto(int nId, String aId, String nTitle, String nContent, String nFilename, int nHit,
			Timestamp nRdate, String nIp) {
		super();
		this.nId = nId;
		this.aId = aId;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nFilename = nFilename;
		this.nHit = nHit;
		this.nRdate = nRdate;
		this.nIp = nIp;
	}
	public int getnId() {
		return nId;
	}
	public void setnId(int nId) {
		this.nId = nId;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getnTitle() {
		return nTitle;
	}
	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}
	public String getnContent() {
		return nContent;
	}
	public void setnContent(String nContent) {
		this.nContent = nContent;
	}
	public String getnFilename() {
		return nFilename;
	}
	public void setnFilename(String nFilename) {
		this.nFilename = nFilename;
	}
	public int getnHit() {
		return nHit;
	}
	public void setnHit(int nHit) {
		this.nHit = nHit;
	}
	public Timestamp getnRdate() {
		return nRdate;
	}
	public void setnRdate(Timestamp nRdate) {
		this.nRdate = nRdate;
	}
	public String getnIp() {
		return nIp;
	}
	public void setnIp(String nIp) {
		this.nIp = nIp;
	}
	@Override
	public String toString() {
		return "NoticeBoardDto [nId=" + nId + ", aId=" + aId + ", nTitle=" + nTitle + ", nContent=" + nContent
				+ ", nFilename=" + nFilename + ", nHit=" + nHit + ", nRdate=" + nRdate + ", nIp=" + nIp + "]";
	}
	
}
