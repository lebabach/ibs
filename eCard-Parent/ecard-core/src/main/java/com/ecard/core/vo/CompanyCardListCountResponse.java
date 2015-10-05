/*
 * CompanyCardListCountResponse
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class CompanyCardListCountResponse extends AbstractCommonRes{
    private List<CompanyCardListCount> companyList;

    /**
     * @return the companyList
     */
    public List<CompanyCardListCount> getCompanyList() {
        return companyList;
    }

    /**
     * @param companyList the companyList to set
     */
    public void setCompanyList(List<CompanyCardListCount> companyList) {
        this.companyList = companyList;
    }
}
