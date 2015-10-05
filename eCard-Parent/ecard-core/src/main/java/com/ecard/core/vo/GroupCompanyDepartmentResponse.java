/*
 * GroupCompanyDepartmentResponse
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class GroupCompanyDepartmentResponse extends AbstractCommonRes{
    private List<GroupDepartmentVO> companyDepartmentList;

    /**
     * @return the companyDepartmentList
     */
    public List<GroupDepartmentVO> getCompanyDepartmentList() {
        return companyDepartmentList;
    }

    /**
     * @param companyDepartmentList the companyDepartmentList to set
     */
    public void setCompanyDepartmentList(List<GroupDepartmentVO> companyDepartmentList) {
        this.companyDepartmentList = companyDepartmentList;
    }
    
}
