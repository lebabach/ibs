/*
 * PosessionCardInfo
 * Mapper belong PossessionCard and CardInfo model
 */
package com.ecard.core.vo;

/**
 *
 * @author vinhla
 */
public class PosessionCardInfo {
    private Integer user_id;
    private Integer card_id;
    private Integer seq;
    private String name; 
    private String position_name; 
    private String department_name;
    private String company_name;
    private String image_file;
    private String tel_number_company;

    /**
     * @return the user_id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the card_id
     */
    public Integer getCard_id() {
        return card_id;
    }

    /**
     * @param card_id the card_id to set
     */
    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
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
        this.seq = seq;
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
     * @return the position_name
     */
    public String getPosition_name() {
        return position_name;
    }

    /**
     * @param position_name the position_name to set
     */
    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    /**
     * @return the department_name
     */
    public String getDepartment_name() {
        return department_name;
    }

    /**
     * @param department_name the department_name to set
     */
    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    /**
     * @return the company_name
     */
    public String getCompany_name() {
        return company_name;
    }

    /**
     * @param company_name the company_name to set
     */
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    /**
     * @return the image_file
     */
    public String getImage_file() {
        return image_file;
    }

    /**
     * @param image_file the image_file to set
     */
    public void setImage_file(String image_file) {
        this.image_file = image_file;
    }

    /**
     * @return the tel_number_company
     */
    public String getTel_number_company() {
        return tel_number_company;
    }

    /**
     * @param tel_number_company the tel_number_company to set
     */
    public void setTel_number_company(String tel_number_company) {
        this.tel_number_company = tel_number_company;
    }
}
