package com.lec.mybag.dto;

import java.sql.Timestamp;

public class LikemyBagDto {
	private int lId;
	private String mId;
	private int bId;
	private Timestamp lRdate;
	public LikemyBagDto() {	}
	public LikemyBagDto(int lId, String mId, int bId, Timestamp lRdate) {
		super();
		this.lId = lId;
		this.mId = mId;
		this.bId = bId;
		this.lRdate = lRdate;
	}
	public int getlId() {
		return lId;
	}
	public void setlId(int lId) {
		this.lId = lId;
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
	public Timestamp getlRdate() {
		return lRdate;
	}
	public void setlRdate(Timestamp lRdate) {
		this.lRdate = lRdate;
	}
	@Override
	public String toString() {
		return "LikemyBagDto [lId=" + lId + ", mId=" + mId + ", bId=" + bId + ", lRdate=" + lRdate + "]";
	}
	
}
