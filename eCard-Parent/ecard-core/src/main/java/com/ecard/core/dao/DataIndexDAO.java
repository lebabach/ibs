package com.ecard.core.dao;

import java.util.Date;
import java.util.List;

import com.ecard.core.model.*;
import com.ecard.core.model.enums.ActionTypeEnum;
import com.ecard.core.model.enums.IndexTypeEnum;
import com.ecard.core.model.enums.PropertyCodeEnum;
import com.ecard.core.model.enums.TableTypeEnum;

public interface DataIndexDAO extends IGenericDao<DataIndex> {
	public void insertDataIndex(DataIndexId dataIndexId);

	public List<DataIndex> findAll(Class clazz);

	public boolean updateDataIndex(IndexTypeEnum indexType, String indexNo);

	public boolean updateDataIndex(String indexNo, IndexTypeEnum indexType);

	public DataIndexId getLastDataIndexBy(IndexTypeEnum indexType, Date updatedDate, DataIndexId lastDataIndex);

	public String insertDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode);

	public String insertDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String companyId);

	public String updateDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String IndexNo);

	public String updateDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String IndexNo, String companyId);

	public String insertOrUpdateDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String IndexNo, String companyId);

	public String insertOrUpdateDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String IndexNo);
	public String updateDataIndexPCOBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String IndexNo);
	
	public DataIndex getDataIndexById(int indexType);
}
