package com.ecard.core.dao;

import java.util.Date;
import java.util.List;

import com.ecard.core.model.DataIndexId;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.enums.ActionTypeEnum;
import com.ecard.core.model.enums.IndexTypeEnum;
import com.ecard.core.model.enums.PropertyCodeEnum;
import com.ecard.core.model.enums.TableTypeEnum;

public interface DataIndexIdDAO{
	 public List<DataIndexId> findAll(Class clazz);
	 public DataIndexId getLastDataIndexBy(IndexTypeEnum indexType, Date updatedDate,DataIndexId lastDataIndex);
	 public String insertDataIndexBy(IndexTypeEnum indexType,ActionTypeEnum actionType, TableTypeEnum tableType, PropertyCodeEnum propertyCode);
	 public String insertDataIndexBy(IndexTypeEnum indexType,ActionTypeEnum actionType, TableTypeEnum tableType, PropertyCodeEnum propertyCode,String companyId);
	 public String updateDataIndexBy(IndexTypeEnum indexType,ActionTypeEnum actionType, TableTypeEnum tableType, PropertyCodeEnum propertyCode,String IndexNo);
	 public String updateDataIndexBy(IndexTypeEnum indexType,ActionTypeEnum actionType, TableTypeEnum tableType, PropertyCodeEnum propertyCode,String IndexNo,String companyId);
	 public String insertOrUpdateDataIndexBy(IndexTypeEnum indexType,ActionTypeEnum actionType, TableTypeEnum tableType, PropertyCodeEnum propertyCode,String IndexNo,String companyId);
	 public String insertOrUpdateDataIndexBy(IndexTypeEnum indexType,ActionTypeEnum actionType, TableTypeEnum tableType, PropertyCodeEnum propertyCode,String IndexNo);
}
