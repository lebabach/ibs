/*
 * DataIndexServiceImpl
 */
package com.ecard.core.service.impl;

import com.ecard.core.dao.DataIndexDAO;
import com.ecard.core.model.enums.*;
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
    DataIndexDAO dataIndexDAO;
    
    public String generateDataIndex(IndexTypeEnum indexTypeEnum, ActionTypeEnum actionTypeEnum, TableTypeEnum tableTypeEnum, PropertyCodeEnum propertyCodeEnum){
        return dataIndexDAO.insertDataIndexBy(indexTypeEnum, actionTypeEnum, tableTypeEnum, propertyCodeEnum);
    }
}
