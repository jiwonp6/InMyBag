package com.lec.mybag.dto;

import java.sql.Timestamp;

public class QnaBoardDto {
	private int qId;
	private String aId;
	private String mId;
	private String qTitle;
	private String qContent;
	private String qFilename;
	private int qHit;
	private Timestamp qRdate;
	private int qGroup;
	private int qStep;
	private int qIndent;
	private String qIp;
	public QnaBoardDto() {	}
	public QnaBoardDto(int qId, String aId, String mId, String qTitle, String qContent, String qFilename, int qHit,
			Timestamp qRdate, int qGroup, int qStep, int qIndent, String qIp) {
		super();
		this.qId = qId;
		this.aId = aId;
		this.mId = mId;
		this.qTitle = qTitle;
		this.qContent = qContent;
		this.qFilename = qFilename;
		this.qHit = qHit;
		this.qRdate = qRdate;
		this.qGroup = qGroup;
		this.qStep = qStep;
		this.qIndent = qIndent;
		this.qIp = qIp;
	}
	public int getqId() {
		return qId;
	}
	public void setqId(int qId) {
		this.qId = qId;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public String getqFilename() {
		return qFilename;
	}
	public void setqFilename(String qFilename) {
		this.qFilename = qFilename;
	}
	public int getqHit() {
		return qHit;
	}
	public void setqHit(int qHit) {
		this.qHit = qHit;
	}
	public Timestamp getqRdate() {
		return qRdate;
	}
	public void setqRdate(Timestamp qRdate) {
		this.qRdate = qRdate;
	}
	public int getqGroup() {
		return qGroup;
	}
	public void setqGroup(int qGroup) {
		this.qGroup = qGroup;
	}
	public int getqStep() {
		return qStep;
	}
	public void setqStep(int qStep) {
		this.qStep = qStep;
	}
	public int getqIndent() {
		return qIndent;
	}
	public void setqIndent(int qIndent) {
		this.qIndent = qIndent;
	}
	public String getqIp() {
		return qIp;
	}
	public void setqIp(String qIp) {
		this.qIp = qIp;
	}
	@Override
	public String toString() {
		return "QnaBoardDto [qId=" + qId + ", aId=" + aId + ", mId=" + mId + ", qTitle=" + qTitle + ", qContent="
				+ qContent + ", qFilename=" + qFilename + ", qHit=" + qHit + ", qRdate=" + qRdate + ", qGroup=" + qGroup
				+ ", qStep=" + qStep + ", qIndent=" + qIndent + ", qIp=" + qIp + "]";
	}
	
}
