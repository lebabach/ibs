/*
 * DataIndexServiceImpl
 */
package com.ecard.core.service.impl;

import com.ecard.core.dao.DataIndexIdDAO;
import com.ecard.core.model.enums.ActionTypeEnum;
import com.ecard.core.model.enums.IndexTypeEnum;
import com.ecard.core.model.enums.PropertyCodeEnum;
import com.ecard.core.model.enums.TableTypeEnum;
import com.ecard.core.service.DataIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vinhla
 */
@Service("dataIndexService")
@Transactional
public class DataIndexServiceImpl implements DataIndexService{
    
    @Autowired
    DataIndexIdDAO dataIndexIdDAO;
    
    public String generateDataIndex(IndexTypeEnum indexTypeEnum, ActionTypeEnum actionTypeEnum, TableTypeEnum tableTypeEnum, PropertyCodeEnum propertyCodeEnum){
        return dataIndexIdDAO.insertDataIndexBy(indexTypeEnum, actionTypeEnum, tableTypeEnum, propertyCodeEnum);
    }
}
