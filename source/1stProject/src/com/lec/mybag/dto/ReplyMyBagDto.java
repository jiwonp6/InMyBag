package com.lec.mybag.dto;

import java.sql.Timestamp;

public class ReplyMyBagDto {
	private int rId;
	private String mId;
	private int bId;
	private String rContent;
	private Timestamp rRdate;
	private int rGroup;
	private int rStep;
	private int rIndent;
	private String rIp;
	public ReplyMyBagDto() {	}
	public ReplyMyBagDto(int rId, String mId, int bId, String rContent, Timestamp rRdate, int rGroup, int rStep,
			int rIndent, String rIp) {
		super();
		this.rId = rId;
		this.mId = mId;
		this.bId = bId;
		this.rContent = rContent;
		this.rRdate = rRdate;
		this.rGroup = rGroup;
		this.rStep = rStep;
		this.rIndent = rIndent;
		this.rIp = rIp;
	}
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public Timestamp getrRdate() {
		return rRdate;
	}
	public void setrRdate(Timestamp rRdate) {
		this.rRdate = rRdate;
	}
	public int getrGroup() {
		return rGroup;
	}
	public void setrGroup(int rGroup) {
		this.rGroup = rGroup;
	}
	public int getrStep() {
		return rStep;
	}
	public void setrStep(int rStep) {
		this.rStep = rStep;
	}
	public int getrIndent() {
		return rIndent;
	}
	public void setrIndent(int rIndent) {
		this.rIndent = rIndent;
	}
	public String getrIp() {
		return rIp;
	}
	public void setrIp(String rIp) {
		this.rIp = rIp;
	}
	@Override
	public String toString() {
		return "ReplymyBagDto [rId=" + rId + ", mId=" + mId + ", bId=" + bId + ", rContent=" + rContent + ", rRdate="
				+ rRdate + ", rGroup=" + rGroup + ", rStep=" + rStep + ", rIndent=" + rIndent + ", rIp=" + rIp + "]";
	}
	
}
