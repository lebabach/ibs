package com.ecard.webapp.vo;

import java.io.Serializable;
import java.util.ArrayList;


public class ListCardDelete implements Serializable{
	private static final long serialVersionUID = 1L;
	ArrayList<ObjectCards> listCardId;
	
	public ArrayList<ObjectCards> getListCardId() {
		return listCardId;
	}
	public void setListCardId(ArrayList<ObjectCards> listCardId) {
		this.listCardId = listCardId;
	}



	
}