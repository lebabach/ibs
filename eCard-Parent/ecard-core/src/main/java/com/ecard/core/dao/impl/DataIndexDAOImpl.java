package com.ecard.core.dao.impl;

import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.LockModeType;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.*;
import com.ecard.core.model.*;
import com.ecard.core.model.enums.ActionTypeEnum;
import com.ecard.core.model.enums.IndexTypeEnum;
import com.ecard.core.model.enums.PropertyCodeEnum;
import com.ecard.core.model.enums.TableTypeEnum;
import com.ecard.core.util.DataIndexUtil;

@Repository("DataIndexDAO")
public class DataIndexDAOImpl extends GenericDao<DataIndex>implements DataIndexDAO {
	public void insertDataIndex(DataIndexId dataIndexId) {
		DataIndex dataindex = new DataIndex();
		dataindex.setId(dataIndexId);
		System.out.println(
				"=============debugs====insert===dataindex=============persist function: insert DataIndex: index_no: "
						+ dataindex.getId().getIndexNo() + " index_type: " + dataindex.getId().getIndexType()
						+ " date: " + dataindex.getId().getCreateDate());
		this.persist(dataindex);
	}

	@Override
	public boolean updateDataIndex(IndexTypeEnum indexType, String indexNo) {
		String sql = "UPDATE DataIndex t SET t.id.indexNo = :newindexNo, t.id.createDate = :createDate WHERE t.id.indexType = :indexType";
		Query query = getEntityManager().createQuery(sql);
		Date date = new Date();
		query.setParameter("newindexNo", indexNo);
		query.setParameter("indexType", indexType.getStatusCode());
		query.setParameter("createDate", date);
		System.out.println(
				"=============debugs=====update===dataindex=========" + printSql(sql, indexType, indexNo, date));
		int result = query.executeUpdate();
		if (result == 0) {
			return false;
		} else {
			return true;
		}
	}

	private String printSql(String sql, IndexTypeEnum indexType, String indexNo, Date date) {
		sql = sql.replace(":newindexNo", indexNo + "");
		sql = sql.replace(":indexType", indexType.getStatusCode() + "");
		sql = sql.replace(":createDate", date + "");
		return sql;
	}

	@Override
	public boolean updateDataIndex(String indexNo, IndexTypeEnum indexType) {
		Query query = getEntityManager()
				.createNativeQuery("UPDATE data_index t SET t.index_no = :newindexNo WHERE t.index_type = :indexType");
		query.setParameter("newindexNo", indexNo);
		query.setParameter("indexType", indexType.getStatusCode());
		int result = query.executeUpdate();
		if (result == 0) {
			return false;
		} else {
			return true;
		}
	}

	public DataIndexId getLastDataIndexBy(IndexTypeEnum indexType, Date updatedDate, DataIndexId lastDataIndex) {
		try {
			List<DataIndexId> dataindexes = this.findAll(DataIndex.class).stream().map(x -> x.getId())
					.collect(Collectors.toList());
			if (dataindexes != null && dataindexes.size() != 0) {
				List<DataIndexId> dataIndexesByIndexType = dataindexes.stream()
						.filter(d -> d.getIndexType() == indexType.getStatusCode())
						.sorted(Comparator.comparing(DataIndexId::getCreateDate).reversed())
						.collect(Collectors.toList());
				lastDataIndex = dataIndexesByIndexType.stream().findFirst().get();

				DataIndexId dataindex = dataIndexesByIndexType.stream()
						.filter(d -> d.getCreateDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
								.isEqual(updatedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
						.findFirst().get();
				return dataindex;
			}
			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public DataIndexId getLastDataIndexBy(IndexTypeEnum indexType) {
		try {
			List<DataIndexId> dataindexes = this.findAll(DataIndex.class).stream().map(x -> x.getId())
					.collect(Collectors.toList());
			if (dataindexes != null && dataindexes.size() != 0) {
				List<DataIndexId> dataIndexesByIndexType = dataindexes.stream()
						.filter(d -> d.getIndexType() == indexType.getStatusCode())
						.sorted(Comparator.comparing(DataIndexId::getCreateDate).reversed())
						.collect(Collectors.toList());
				return dataIndexesByIndexType.stream().findFirst().get();
			}
			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String insertDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode) {
		DataIndexId lastDataIndex = null;
		DataIndexId dataIndex = getLastDataIndexBy(indexType, new Date(), lastDataIndex);
		DataIndexId dataIndexInsert = new DataIndexId();
		String formatIdAfterGenerating = "";
		if (dataIndex != null) {
			formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
					DataIndexUtil.getSequenceCodeFrom(dataIndex.getIndexNo()),
					DataIndexUtil.getPropertyCodeFrom(dataIndex.getIndexNo()), propertyCode, actionType);

		} else {
			formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType, 0, 0, propertyCode, actionType);
		}
		dataIndexInsert.setIndexType(indexType.getStatusCode());
		dataIndexInsert.setIndexNo(formatIdAfterGenerating);
		dataIndexInsert.setCreateDate(new Date());
		this.insertDataIndex(dataIndexInsert);
		return formatIdAfterGenerating;
	}

	public String insertDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String companyId) {
		DataIndexId lastDataIndex = null;
		DataIndexId dataIndex = getLastDataIndexBy(indexType, new Date(), lastDataIndex);
		DataIndexId dataIndexInsert = new DataIndexId();
		String formatIdAfterGenerating = "";
		if (dataIndex != null) {
			formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
					DataIndexUtil.getSequenceCodeFrom(dataIndex.getIndexNo()), Integer.parseInt(companyId),
					propertyCode, actionType);

		} else {
			formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType, 0, Integer.parseInt(companyId),
					propertyCode, actionType);
		}
		dataIndexInsert.setIndexType(indexType.getStatusCode());
		dataIndexInsert.setIndexNo(formatIdAfterGenerating);
		dataIndexInsert.setCreateDate(new Date());
		this.insertDataIndex(dataIndexInsert);
		return formatIdAfterGenerating;
	}

	public String updateDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String IndexNo) {
		DataIndexId dataIndex = new DataIndexId();
		String formatIdAfterGenerating = "";
		if (IndexNo == null || IndexNo == "") {
			getLastDataIndexBy(indexType, new Date(), dataIndex);
		} else {
			// dataIndex=dataIndexDAO.findAll(DataIndex.class).stream().map(x->x.getId()).filter(x->x.getIndexNo().equals(IndexNo)).findFirst().get();
			List<DataIndexId> dataIndexIds = this.findAll(DataIndex.class).stream().map(x -> x.getId())
					.filter(x -> x.getIndexNo().equals(IndexNo)).collect(Collectors.toList());
			if (dataIndexIds != null && dataIndexIds.size() > 0) {
				dataIndex = dataIndexIds.stream().findFirst().get();
			} else {
				dataIndex = null;
			}
		}

		if (dataIndex != null) {
			formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
					DataIndexUtil.getSequenceCodeFrom(dataIndex.getIndexNo()),
					DataIndexUtil.getPropertyCodeFrom(dataIndex.getIndexNo()),
					PropertyCodeEnum.findByName(dataIndex.getIndexNo().substring(dataIndex.getIndexNo().length() - 1)),
					actionType);
			// dataIndexDAO.updateDataIndex(dataIndex,formatIdAfterGenerating);
		} else {
			formatIdAfterGenerating = this.insertDataIndexBy(indexType, ActionTypeEnum.Insert, tableType, propertyCode);
		}

		return formatIdAfterGenerating;
	}

	public String updateDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String IndexNo, String companyId) {
		DataIndexId dataIndex = new DataIndexId();
		String formatIdAfterGenerating = "";
		if (IndexNo == null || IndexNo == "") {
			getLastDataIndexBy(indexType, new Date(), dataIndex);
		} else {
			// dataIndex=dataIndexDAO.findAll(DataIndex.class).stream().map(x->x.getId()).filter(x->x.getIndexNo().equals(IndexNo)).findFirst().get();
			List<DataIndexId> dataIndexIds = this.findAll(DataIndex.class).stream().map(x -> x.getId())
					.filter(x -> x.getIndexNo().equals(IndexNo)).collect(Collectors.toList());
			if (dataIndexIds != null && dataIndexIds.size() > 0) {
				dataIndex = dataIndexIds.stream().findFirst().get();
			} else {
				dataIndex = null;
			}
		}

		if (dataIndex != null) {
			formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
					DataIndexUtil.getSequenceCodeFrom(dataIndex.getIndexNo()), Integer.parseInt(companyId),
					PropertyCodeEnum.findByName(dataIndex.getIndexNo().substring(dataIndex.getIndexNo().length() - 1)),
					actionType);
			// dataIndexDAO.updateDataIndex(dataIndex,formatIdAfterGenerating);
		} else {
			formatIdAfterGenerating = this.insertDataIndexBy(indexType, ActionTypeEnum.Insert, tableType, propertyCode,
					companyId);
		}

		return formatIdAfterGenerating;
	}

	// bach.le insert or update dataindex
	// https://livepass.backlog.jp/view/MEISHI-695
	// https://livepass.backlog.jp/view/MEISHI-694
	
	@Transactional
	public String insertOrUpdateDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String IndexNo, String companyId) {
		String formatIdAfterGenerating = StringUtils.EMPTY;
		DataIndex dataIndex = this.getDataIndexById(indexType.getStatusCode());

		if (dataIndex!= null) {
			// found ==> update
			if (!StringUtils.isEmpty(IndexNo)) {
				formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
						DataIndexUtil.getSequenceCodeFrom(IndexNo), Integer.parseInt(companyId),
						PropertyCodeEnum.findByName(IndexNo.substring(IndexNo.length() - 1)), actionType,
						DataIndexUtil.getDateFrom(IndexNo, propertyCode, actionType), IndexNo);
				// dataIndexDAO.updateDataIndex(formatIdAfterGenerating,indexType);
			} else {
				DataIndexId dataIndexId=dataIndex.getId();
				if (dataIndexId != null) {
					formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
							DataIndexUtil.getSequenceCodeFrom(dataIndexId.getIndexNo()), Integer.parseInt(companyId),
							propertyCode, actionType,
							DataIndexUtil.getDateFrom(dataIndexId.getIndexNo(), propertyCode, actionType),
							dataIndexId.getIndexNo());
					this.updateDataIndex(indexType, formatIdAfterGenerating);
				}
			}

		} else {
			// not found ==> insert
			DataIndexId dataIndexInsert = new DataIndexId();
			formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType, 0, Integer.parseInt(companyId), propertyCode, actionType);
			dataIndexInsert.setIndexType(indexType.getStatusCode());
			dataIndexInsert.setIndexNo(formatIdAfterGenerating);
			dataIndexInsert.setCreateDate(new Date());
			this.insertDataIndex(dataIndexInsert);
		}
		return formatIdAfterGenerating;
	}
	
	@Transactional
	public String insertOrUpdateDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String IndexNo) {
		String formatIdAfterGenerating = StringUtils.EMPTY;
		/*List<DataIndexId> dataIndexs = this.findAll(DataIndex.class).stream().map(x -> x.getId())
				.filter(x -> x.getIndexType() == indexType.getStatusCode()).collect(Collectors.toList());*/
		DataIndex dataIndex = this.getDataIndexById(indexType.getStatusCode());
		
		if (dataIndex!= null) {
			// found ==> update
			// if is not empty
			if (!StringUtils.isEmpty(IndexNo)) {
				formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
						DataIndexUtil.getSequenceCodeFrom(IndexNo), DataIndexUtil.getPropertyCodeFrom(IndexNo),
						PropertyCodeEnum.findByName(IndexNo.substring(IndexNo.length() - 1)), actionType,
						DataIndexUtil.getDateFrom(IndexNo, propertyCode, actionType), IndexNo);
				// dataIndexDAO.updateDataIndex(formatIdAfterGenerating,indexType);
			} else {
				// if is empty
				DataIndexId dataIndexId=dataIndex.getId();
				if (dataIndexId != null) {
					formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
							DataIndexUtil.getSequenceCodeFrom(dataIndexId.getIndexNo()),
							DataIndexUtil.getPropertyCodeFrom(dataIndexId.getIndexNo()), propertyCode, actionType,
							DataIndexUtil.getDateFrom(dataIndexId.getIndexNo(), propertyCode, actionType),
							dataIndexId.getIndexNo());
					this.updateDataIndex(indexType, formatIdAfterGenerating);
				}
			}

		} else {
			// not found ==> insert
			DataIndexId dataIndexInsert = new DataIndexId();
			formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType, 0, 0, propertyCode, actionType);
			dataIndexInsert.setIndexType(indexType.getStatusCode());
			dataIndexInsert.setIndexNo(formatIdAfterGenerating);
			dataIndexInsert.setCreateDate(new Date());
			this.insertDataIndex(dataIndexInsert);
		}
		return formatIdAfterGenerating;
	}

	public DataIndex getDataIndexById(int indexType) {
		Query query = getEntityManager().createQuery("SELECT di FROM DataIndex di WHERE di.id.indexType = :indexType");
		query.setParameter("indexType", indexType);
		query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
		return (DataIndex) getOrNull(query);	
	}
	
	@Transactional
	public String updateDataIndexPCOBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String IndexNo) {
		String formatIdAfterGenerating = StringUtils.EMPTY;
		DataIndex dataIndex = this.getDataIndexById(indexType.getStatusCode());
		
		if (dataIndex!= null) {
				formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
						DataIndexUtil.getSequenceCodeFrom(IndexNo), DataIndexUtil.getPropertyCodeFrom(IndexNo),
						PropertyCodeEnum.findByName(IndexNo.substring(IndexNo.length() - 1)), actionType,
						DataIndexUtil.getDateFrom(IndexNo, propertyCode, actionType), IndexNo);
				this.updateDataIndex(indexType,formatIdAfterGenerating);
			

		} 
		return formatIdAfterGenerating;
	}
}
