/*
 * GroupCompanyInfoResponse
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class GroupCompanyInfoResponse extends AbstractCommonRes{
    private List<GroupCompanyInfoVo> groupCompanyInfoList;

    /**
     * @return the groupCompanyInfoList
     */
    public List<GroupCompanyInfoVo> getGroupCompanyInfoList() {
        return groupCompanyInfoList;
    }

    /**
     * @param groupCompanyInfoList the groupCompanyInfoList to set
     */
    public void setGroupCompanyInfoList(List<GroupCompanyInfoVo> groupCompanyInfoList) {
        this.groupCompanyInfoList = groupCompanyInfoList;
    }
}
