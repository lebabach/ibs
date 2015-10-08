/*
 * SearchInfo
 */
package com.ecard.core.vo;

/**
 *
 * @author vinhla
 */
public class SearchInfo {
    private String freeText;
    private String title;
    private String owner;
    private String company;
    private String department;
    private String position;
    private String name;         
    private int seq;
    private int parameterFlg;
    private int id;
    public SearchInfo(){}
    
    public SearchInfo(String freeText, String title, String owner, String company, String department,String position, String name, int seq, int parameterFlg){
        this.freeText = freeText;
        this.title = title;
        this.owner = owner;
        this.company = company;
        this.department = department;
        this.position = position;
        this.name = name;
        this.seq = seq;
        this.parameterFlg = parameterFlg;        
    }
    
    /**
     * @return the freeText
     */
    public String getFreeText() {
        return freeText;
    }

    /**
     * @param freeText the freeText to set
     */
    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    /**
     * @return the seq
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * @param seq the seq to set
     */
    public void setSeq(Integer seq) {
        this.setSeq((int) seq);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param seq the seq to set
     */
    public void setSeq(int seq) {
        this.seq = seq;
    }

    /**
     * @return the parameterFlg
     */
    public int getParameterFlg() {
        return parameterFlg;
    }

    /**
     * @param parameterFlg the parameterFlg to set
     */
    public void setParameterFlg(int parameterFlg) {
        this.parameterFlg = parameterFlg;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
   
}
