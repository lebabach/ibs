package com.ecard.webapp.vo;

import java.util.List;

public class DataPagingJsonVO<T> {
	int draw;
	long recordsTotal;
	long recordsFiltered;
	List<T> data;
	
	
	/**
	 * 
	 */
	public DataPagingJsonVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param draw
	 * @param recordsTotal
	 * @param recordsFiltered
	 * @param data
	 */
	public DataPagingJsonVO(int draw, int recordsTotal, int recordsFiltered, List<T> data) {
		super();
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}
	/**
	 * @return the draw
	 */
	public int getDraw() {
		return draw;
	}
	/**
	 * @param draw the draw to set
	 */
	public void setDraw(int draw) {
		this.draw = draw;
	}
	/**
	 * @return the recordsTotal
	 */
	public long getRecordsTotal() {
		return recordsTotal;
	}
	/**
	 * @param recordsTotal the recordsTotal to set
	 */
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	/**
	 * @return the recordsFiltered
	 */
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	/**
	 * @param recordsFiltered the recordsFiltered to set
	 */
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}
}
