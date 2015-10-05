/**
 * 
 */
package com.ecard.core.service.converter;

import com.ecard.core.vo.GroupCompanyInfoVo;
import com.ecard.core.vo.GroupDepartmentVO;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;


/**
 * @author Windows
 *
 */
public final class GroupCompanyInfoConverter {
    public static List<GroupCompanyInfoVo>  convertGroupCompanyInfoList(List<com.ecard.core.model.GroupCompanyInfo> groupCompanyInfoList) throws IllegalAccessException, InvocationTargetException{
        List<GroupCompanyInfoVo> groupCompanyList = new ArrayList<GroupCompanyInfoVo>();
        if (CollectionUtils.isNotEmpty(groupCompanyInfoList)){
            GroupCompanyInfoVo groupCompanyInfo;
            for (com.ecard.core.model.GroupCompanyInfo groupCompanyInfoModel : groupCompanyInfoList) {
                groupCompanyInfo = new GroupCompanyInfoVo();
                groupCompanyInfo.setGroupCompanyId(groupCompanyInfoModel.getGroupCompanyId());
                groupCompanyInfo.setGroupCompanyName(groupCompanyInfoModel.getGroupCompanyName());
                groupCompanyInfo.setGroupCompanyNameKana(groupCompanyInfoModel.getGroupCompanyNameKana());
                groupCompanyInfo.setCreateDate(groupCompanyInfoModel.getCreateDate());
                groupCompanyInfo.setUpdateDate(groupCompanyInfoModel.getUpdateDate());
                groupCompanyInfo.setOperaterId(groupCompanyInfoModel.getOperaterId());
                groupCompanyList.add(groupCompanyInfo);
            }
        }
        return groupCompanyList;
    }
    
    public static List<GroupDepartmentVO>  convertGroupCompanyDepartmentList(List<com.ecard.core.model.GroupCompanyDepartment> groupCompanyDepartmentList) throws IllegalAccessException, InvocationTargetException{
        List<GroupDepartmentVO> groupDepartmentVOList = new ArrayList<GroupDepartmentVO>();
        if (CollectionUtils.isNotEmpty(groupCompanyDepartmentList)){
            GroupDepartmentVO groupDepartmentVO;
            for (com.ecard.core.model.GroupCompanyDepartment groupCompanyDepartmentModel : groupCompanyDepartmentList) {
                groupDepartmentVO = new GroupDepartmentVO();
                groupDepartmentVO.setGroupCompanyDepartmentName(groupCompanyDepartmentModel.getId().getGroupCompanyDepartmentName());
                groupDepartmentVO.setGroupCompanyId(groupCompanyDepartmentModel.getId().getGroupCompanyId());
                groupDepartmentVO.setSeq(groupCompanyDepartmentModel.getId().getSeq());
                groupDepartmentVOList.add(groupDepartmentVO);
            }
        }
        return groupDepartmentVOList;
    }
        
}
