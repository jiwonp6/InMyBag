package com.lec.mybag.dto;

public class MyBagCanvasDto {
	private int cId;
	private int bId;
	private int cX;
	private int cY;
	private String cContent;
	public MyBagCanvasDto() {	}
	public MyBagCanvasDto(int cId, int bId, int cX, int cY, String cContent) {
		this.cId = cId;
		this.bId = bId;
		this.cX = cX;
		this.cY = cY;
		this.cContent = cContent;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public int getcX() {
		return cX;
	}
	public void setcX(int cX) {
		this.cX = cX;
	}
	public int getcY() {
		return cY;
	}
	public void setcY(int cY) {
		this.cY = cY;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	@Override
	public String toString() {
		return "MyBagCanvasDto [cId=" + cId + ", bId=" + bId + ", cX=" + cX + ", cY=" + cY + ", cContent=" + cContent
				+ "]";
	}
	
}
