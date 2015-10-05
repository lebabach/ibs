package com.ecard.core.dao;

import java.util.List;

import com.ecard.core.model.*;
import com.ecard.core.model.enums.IndexTypeEnum;

public interface DataIndexDAO extends IGenericDao<DataIndex>{
	public void insertDataIndex(DataIndexId dataIndexId);
	public List<DataIndex> findAll(Class clazz);
	public boolean updateDataIndex(IndexTypeEnum indexType,String indexNo);
	public boolean updateDataIndex(String indexNo,IndexTypeEnum indexType);
}
