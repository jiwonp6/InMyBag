package com.lec.mybag.dto;

public class AdminDto {
	private String aId;
	private String aPw;
	private String aName;
	private int aKing;
	public AdminDto() {}
	public AdminDto(String aId, String aPw, String aName, int aKing) {
		this.aId = aId;
		this.aPw = aPw;
		this.aName = aName;
		this.aKing = aKing;
	}
	public AdminDto(String aId, String aPw, String aName) {
		this.aId = aId;
		this.aPw = aPw;
		this.aName = aName;
	}
	
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getaPw() {
		return aPw;
	}
	public void setaPw(String aPw) {
		this.aPw = aPw;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public int getaKing() {
		return aKing;
	}
	public void setaKing(int aKing) {
		this.aKing = aKing;
	}
	@Override
	public String toString() {
		return "AdminDto [aId=" + aId + ", aPw=" + aPw + ", aName=" + aName + ", aKing=" + aKing + "]";
	}
	
}
