package com.lec.mybag.dto;

import java.sql.Timestamp;

public class ItemBoardDto {
	private int iId;
	private String mId;
	private String iTitle;
	private String iContent;
	private String iFilename;
	private int iHit;
	private Timestamp iRdate;
	private int iGroup;
	private int iStep;
	private int iIndent;
	private String iIp;
	public ItemBoardDto() {	}
	public ItemBoardDto(int iId, String mId, String iTitle, String iContent, String iFilename, int iHit,
			Timestamp iRdate, int iGroup, int iStep, int iIndent, String iIp) {
		super();
		this.iId = iId;
		this.mId = mId;
		this.iTitle = iTitle;
		this.iContent = iContent;
		this.iFilename = iFilename;
		this.iHit = iHit;
		this.iRdate = iRdate;
		this.iGroup = iGroup;
		this.iStep = iStep;
		this.iIndent = iIndent;
		this.iIp = iIp;
	}
	public int getiId() {
		return iId;
	}
	public void setiId(int iId) {
		this.iId = iId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getiTitle() {
		return iTitle;
	}
	public void setiTitle(String iTitle) {
		this.iTitle = iTitle;
	}
	public String getiContent() {
		return iContent;
	}
	public void setiContent(String iContent) {
		this.iContent = iContent;
	}
	public String getiFilename() {
		return iFilename;
	}
	public void setiFilename(String iFilename) {
		this.iFilename = iFilename;
	}
	public int getiHit() {
		return iHit;
	}
	public void setiHit(int iHit) {
		this.iHit = iHit;
	}
	public Timestamp getiRdate() {
		return iRdate;
	}
	public void setiRdate(Timestamp iRdate) {
		this.iRdate = iRdate;
	}
	public int getiGroup() {
		return iGroup;
	}
	public void setiGroup(int iGroup) {
		this.iGroup = iGroup;
	}
	public int getiStep() {
		return iStep;
	}
	public void setiStep(int iStep) {
		this.iStep = iStep;
	}
	public int getiIndent() {
		return iIndent;
	}
	public void setiIndent(int iIndent) {
		this.iIndent = iIndent;
	}
	public String getiIp() {
		return iIp;
	}
	public void setiIp(String iIp) {
		this.iIp = iIp;
	}
	@Override
	public String toString() {
		return "ItemBoardDto [iId=" + iId + ", mId=" + mId + ", iTitle=" + iTitle + ", iContent=" + iContent
				+ ", iFilename=" + iFilename + ", iHit=" + iHit + ", iRdate=" + iRdate + ", iGroup=" + iGroup
				+ ", iStep=" + iStep + ", iIndent=" + iIndent + ", iIp=" + iIp + "]";
	}

}
