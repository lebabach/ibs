package com.ecard.core.dao.impl;

import java.util.Date;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecard.core.dao.*;
import com.ecard.core.model.*;
import com.ecard.core.model.enums.IndexTypeEnum;
@Repository("DataIndexDAO")
public class DataIndexDAOImpl extends GenericDao<DataIndex> implements DataIndexDAO{
	public void insertDataIndex(DataIndexId dataIndexId){
		DataIndex dataindex=new DataIndex();
		dataindex.setId(dataIndexId);
		System.out.println("=============debugs====insert===dataindex=============persist function: insert DataIndex: index_no: "+dataindex.getId().getIndexNo()+" index_type: "+dataindex.getId().getIndexType()+" date: "+dataindex.getId().getCreateDate());
		this.persist(dataindex);
	}
	
	@Override
	public boolean updateDataIndex(IndexTypeEnum indexType,String indexNo){
		String sql="UPDATE DataIndex t SET t.id.indexNo = :newindexNo, t.id.createDate = :createDate WHERE t.id.indexType = :indexType";
		Query query = getEntityManager().createQuery(sql);
		Date date=new Date();
		query.setParameter("newindexNo", indexNo);
		query.setParameter("indexType", indexType.getStatusCode());
		query.setParameter("createDate", date);
		System.out.println("=============debugs=====update===dataindex========="+printSql(sql,indexType,indexNo,date));
		int result = query.executeUpdate();
        if(result == 0){
            return false;
        } else {
            return true;
        }
	}
	
	private String printSql(String sql,IndexTypeEnum indexType,String indexNo,Date date){
		sql=sql.replace(":newindexNo", indexNo+"");
		sql=sql.replace(":indexType", indexType.getStatusCode()+"");
		sql=sql.replace(":createDate", date+"");
    	return sql;
    }
	
	@Override
	public boolean updateDataIndex(String indexNo,IndexTypeEnum indexType){
		Query query = getEntityManager().createNativeQuery("UPDATE data_index t SET t.index_no = :newindexNo WHERE t.index_type = :indexType");
		query.setParameter("newindexNo", indexNo);
		query.setParameter("indexType", indexType.getStatusCode());
		int result = query.executeUpdate();
        if(result == 0){
            return false;
        } else {
            return true;
        }
	}
}
