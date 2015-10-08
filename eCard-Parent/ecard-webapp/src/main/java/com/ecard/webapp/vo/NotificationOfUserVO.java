package com.ecard.webapp.vo;

import java.util.Date;

public class NotificationOfUserVO {
	private String contents;
	private Date date;
	private int id;
	private String image;
	private Integer read_flg;
	public Integer getRead_flg() {
		return read_flg;
	}
	public void setRead_flg(Integer read_flg) {
		this.read_flg = read_flg;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
