package com.ecard.core.dao.impl;

import java.io.Console;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecard.core.dao.DataIndexDAO;
import com.ecard.core.dao.DataIndexIdDAO;
import com.ecard.core.dao.UserInfoDAO;
import com.ecard.core.model.DataIndex;
import com.ecard.core.model.DataIndexId;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.enums.*;
import com.ecard.core.util.DataIndexUtil;

@Repository("DataIndexIdDAO")
public class DataIndexIdDAOImpl extends GenericDao<DataIndexId>implements DataIndexIdDAO {
	@Autowired
	DataIndexDAO dataIndexDAO;

	public DataIndexId getLastDataIndexBy(IndexTypeEnum indexType, Date updatedDate, DataIndexId lastDataIndex) {
		try {
			List<DataIndexId> dataindexes = dataIndexDAO.findAll(DataIndex.class).stream().map(x -> x.getId())
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
			List<DataIndexId> dataindexes = dataIndexDAO.findAll(DataIndex.class).stream().map(x -> x.getId())
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
		dataIndexDAO.insertDataIndex(dataIndexInsert);
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
		dataIndexDAO.insertDataIndex(dataIndexInsert);
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
			List<DataIndexId> dataIndexIds = dataIndexDAO.findAll(DataIndex.class).stream().map(x -> x.getId())
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
			List<DataIndexId> dataIndexIds = dataIndexDAO.findAll(DataIndex.class).stream().map(x -> x.getId())
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

	public String insertOrUpdateDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String IndexNo) {
		String formatIdAfterGenerating = StringUtils.EMPTY;
		List<DataIndexId> dataIndexs = dataIndexDAO.findAll(DataIndex.class).stream().map(x -> x.getId())
				.filter(x -> x.getIndexType() == indexType.getStatusCode()).collect(Collectors.toList());
		if (dataIndexs != null && dataIndexs.size() > 0) {
			// found ==> update
			// if is not empty
			System.out.println("========================SIZE=============: " + dataIndexs.size());
			if (!StringUtils.isEmpty(IndexNo)) {
				formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
						DataIndexUtil.getSequenceCodeFrom(IndexNo), DataIndexUtil.getPropertyCodeFrom(IndexNo),
						PropertyCodeEnum.findByName(IndexNo.substring(IndexNo.length() - 1)), actionType,
						DataIndexUtil.getDateFrom(IndexNo, propertyCode, actionType), IndexNo);
				// dataIndexDAO.updateDataIndex(formatIdAfterGenerating,indexType);
			} else {
				// if is empty
				DataIndexId dataindex = dataIndexs.stream().findFirst().get();
				if (dataindex != null) {
					formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
							DataIndexUtil.getSequenceCodeFrom(dataindex.getIndexNo()),
							DataIndexUtil.getPropertyCodeFrom(dataindex.getIndexNo()), propertyCode, actionType,
							DataIndexUtil.getDateFrom(dataindex.getIndexNo(), propertyCode, actionType),
							dataindex.getIndexNo());
					dataIndexDAO.updateDataIndex(indexType, formatIdAfterGenerating);
				}
			}

		} else {
			System.out.println("========================SIZE=============: " + 0);
			// not found ==> insert
			DataIndexId dataIndexInsert = new DataIndexId();
			formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType, 0, 0, propertyCode, actionType);
			dataIndexInsert.setIndexType(indexType.getStatusCode());
			dataIndexInsert.setIndexNo(formatIdAfterGenerating);
			dataIndexInsert.setCreateDate(new Date());
			dataIndexDAO.insertDataIndex(dataIndexInsert);
		}
		return formatIdAfterGenerating;
	}

	public String insertOrUpdateDataIndexBy(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType,
			PropertyCodeEnum propertyCode, String IndexNo, String companyId) {
		String formatIdAfterGenerating = StringUtils.EMPTY;
		List<DataIndexId> dataIndexs = dataIndexDAO.findAll(DataIndex.class).stream().map(x -> x.getId())
				.filter(x -> x.getIndexType() == indexType.getStatusCode()).collect(Collectors.toList());
		
		if (dataIndexs != null && dataIndexs.size() > 0) {
			System.out.println("========================SIZE=============: " + dataIndexs.size());
			// found ==> update
			if (!StringUtils.isEmpty(IndexNo)) {
				formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
						DataIndexUtil.getSequenceCodeFrom(IndexNo), Integer.parseInt(companyId),
						PropertyCodeEnum.findByName(IndexNo.substring(IndexNo.length() - 1)), actionType,
						DataIndexUtil.getDateFrom(IndexNo, propertyCode, actionType), IndexNo);
				// dataIndexDAO.updateDataIndex(formatIdAfterGenerating,indexType);
			} else {
				DataIndexId dataindex = dataIndexs.stream().findFirst().get();
				if (dataindex != null) {
					formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
							DataIndexUtil.getSequenceCodeFrom(dataindex.getIndexNo()), Integer.parseInt(companyId),
							propertyCode, actionType,
							DataIndexUtil.getDateFrom(dataindex.getIndexNo(), propertyCode, actionType),
							dataindex.getIndexNo());
					dataIndexDAO.updateDataIndex(indexType, formatIdAfterGenerating);
				}
			}

		} else {
			System.out.println("========================SIZE=============: " + 0);
			// not found ==> insert
			DataIndexId dataIndexInsert = new DataIndexId();
			formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType, 0, 0, propertyCode, actionType);
			dataIndexInsert.setIndexType(indexType.getStatusCode());
			dataIndexInsert.setIndexNo(formatIdAfterGenerating);
			dataIndexInsert.setCreateDate(new Date());
			dataIndexDAO.insertDataIndex(dataIndexInsert);
		}
		return formatIdAfterGenerating;
	}

}
