/*
 * CompanyCardInfoMapRes
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class CompanyCardInfoRes extends AbstractCommonRes{
    private List<CardInfoModel> companyList;

    /**
     * @return the companyList
     */
    public List<CardInfoModel> getCompanyList() {
        return companyList;
    }

    /**
     * @param companyList the companyList to set
     */
    public void setCompanyList(List<CardInfoModel> companyList) {
        this.companyList = companyList;
    }
}
