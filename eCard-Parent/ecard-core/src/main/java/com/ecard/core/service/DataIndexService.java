/*
 * DataIndexService
 */
package com.ecard.core.service;

import com.ecard.core.model.enums.ActionTypeEnum;
import com.ecard.core.model.enums.IndexTypeEnum;
import com.ecard.core.model.enums.PropertyCodeEnum;
import com.ecard.core.model.enums.TableTypeEnum;

/**
 *
 * @author vinhla
 */
public interface DataIndexService {
    public String generateDataIndex(IndexTypeEnum indexTypeEnum, ActionTypeEnum actionTypeEnum, TableTypeEnum tableTypeEnum, PropertyCodeEnum propertyCodeEnum);
}
