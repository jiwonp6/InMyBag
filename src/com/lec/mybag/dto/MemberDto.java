package com.lec.mybag.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberDto {
	private String mId;
	private String mPw;
	private String mName;
	private Date mBirth;
	private String mEmail;
	private Timestamp mRdate;
	public MemberDto() {	}
	public MemberDto(String mId, String mPw, String mName, Date mBirth, String mEmail, Timestamp mRdate) {
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mBirth = mBirth;
		this.mEmail = mEmail;
		this.mRdate = mRdate;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public Date getmBirth() {
		return mBirth;
	}
	public void setmBirth(Date mBirth) {
		this.mBirth = mBirth;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public Timestamp getmRdate() {
		return mRdate;
	}
	public void setmRdate(Timestamp mRdate) {
		this.mRdate = mRdate;
	}
	@Override
	public String toString() {
		return "MemberDto [mId=" + mId + ", mPw=" + mPw + ", mName=" + mName + ", mBirth=" + mBirth + ", mEmail="
				+ mEmail + ", mRdate=" + mRdate + "]";
	}
	
}
