/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.vo;

/**
 *
 * @author HienTu
 */
public class CardInfoName {
    private String name;
    private String lastName;
    private String firstName;
    private String nameKana;
    private String lastNameKana;
    private String firstNameKana;

    
    public CardInfoName(){}
    
    public CardInfoName(String name, String lastName, String firstName, String nameKana, String lastNameKana, String firstNameKana){
        this.name = name;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nameKana = nameKana;
        this.lastNameKana = lastNameKana;
        this.firstNameKana = firstNameKana;              
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
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the nameKana
     */
    public String getNameKana() {
        return nameKana;
    }

    /**
     * @param nameKana the nameKana to set
     */
    public void setNameKana(String nameKana) {
        this.nameKana = nameKana;
    }

    /**
     * @return the lastNameKana
     */
    public String getLastNameKana() {
        return lastNameKana;
    }

    /**
     * @param lastNameKana the lastNameKana to set
     */
    public void setLastNameKana(String lastNameKana) {
        this.lastNameKana = lastNameKana;
    }

    /**
     * @return the firstNameKana
     */
    public String getFirstNameKana() {
        return firstNameKana;
    }

    /**
     * @param firstNameKana the firstNameKana to set
     */
    public void setFirstNameKana(String firstNameKana) {
        this.firstNameKana = firstNameKana;
    }
    
    
    
    
}
