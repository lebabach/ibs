/*
 * HomeResponse
 */
package com.ecard.core.vo;

import java.math.BigInteger;

/**
 *
 * @author vinhla
 */
public class HomeResponse extends AbstractCommonRes {
    private String name;
    private String lastName;
    private String firstName;
    private String nameKana;
    private String lastNameKana;
    private String firstNameKana;
    private BigInteger recentCardCnt;
    private Long possessionCardCnt;
    private Long inputWaitCnt;
    private BigInteger connectCnt;
    private BigInteger recentConnectCnt;
    private BigInteger noticeCnt;
    private BigInteger recentPossessionCardCnt;
    
//    private StatusInfo statusInfo;

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
     * @return the recentCardCnt
     */
    public BigInteger getRecentCardCnt() {
        return recentCardCnt;
    }

    /**
     * @param recentCardCnt the recentCardCnt to set
     */
    public void setRecentCardCnt(BigInteger recentCardCnt) {
        this.recentCardCnt = recentCardCnt;
    }

    /**
     * @return the possessionCardCnt
     */
    public Long getPossessionCardCnt() {
        return possessionCardCnt;
    }

    /**
     * @param possessionCardCnt the possessionCardCnt to set
     */
    public void setPossessionCardCnt(Long possessionCardCnt) {
        this.possessionCardCnt = possessionCardCnt;
    }

    /**
     * @return the inputWaitCnt
     */
    public Long getInputWaitCnt() {
        return inputWaitCnt;
    }

    /**
     * @param inputWaitCnt the inputWaitCnt to set
     */
    public void setInputWaitCnt(Long inputWaitCnt) {
        this.inputWaitCnt = inputWaitCnt;
    }

    /**
     * @return the connectCnt
     */
    public BigInteger getConnectCnt() {
        return connectCnt;
    }

    /**
     * @param connectCnt the connectCnt to set
     */
    public void setConnectCnt(BigInteger connectCnt) {
        this.connectCnt = connectCnt;
    }

    /**
     * @return the recentConnectCnt
     */
    public BigInteger getRecentConnectCnt() {
        return recentConnectCnt;
    }

    /**
     * @param recentConnectCnt the recentConnectCnt to set
     */
    public void setRecentConnectCnt(BigInteger recentConnectCnt) {
        this.recentConnectCnt = recentConnectCnt;
    }

    /**
     * @return the noticeCnt
     */
    public BigInteger getNoticeCnt() {
        return noticeCnt;
    }

    /**
     * @param noticeCnt the noticeCnt to set
     */
    public void setNoticeCnt(BigInteger noticeCnt) {
        this.noticeCnt = noticeCnt;
    }

    /**
     * @return the recentPossessionCardCnt
     */
    public BigInteger getRecentPossessionCardCnt() {
        return recentPossessionCardCnt;
    }

    /**
     * @param recentPossessionCardCnt the recentPossessionCardCnt to set
     */
    public void setRecentPossessionCardCnt(BigInteger recentPossessionCardCnt) {
        this.recentPossessionCardCnt = recentPossessionCardCnt;
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
