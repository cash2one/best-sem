package com.perfect.db.mongodb.impl;

import com.perfect.core.AppContext;
import com.perfect.dao.report.BasisReportDAO;
import com.perfect.db.mongodb.base.AbstractUserBaseDAOImpl;
import com.perfect.db.mongodb.base.BaseMongoTemplate;
import com.perfect.dto.StructureReportDTO;
import com.perfect.dto.account.AccountReportDTO;
import com.perfect.entity.account.AccountReportEntity;
import com.perfect.entity.report.StructureReportEntity;
import com.perfect.utils.DateUtils;
import com.perfect.utils.ObjectUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.perfect.utils.DateUtils.KEY_DATE;

/**
 * Created by SubDong on 2014/8/6.
 */
//@Repository("basisReportDAO")
@Component
public class BasisReportDAOImpl extends AbstractUserBaseDAOImpl<StructureReportDTO, Long> implements BasisReportDAO {
    @Override
    public List<StructureReportDTO> getUnitReportDate(String userTable, Long dataId, String dataName) {
         
        if (dataId.intValue() != 0 && !dataName.equals("0")) {
            List<StructureReportEntity> objectList = BaseMongoTemplate.getUserReportMongo().find(Query.query(Criteria.where(dataName).is(dataId).and(ACCOUNT_ID).is(AppContext.getAccountId())), getEntityClass(), userTable);
            List<StructureReportDTO> structureReportDTOs = ObjectUtils.convert(objectList, getDTOClass());
            return structureReportDTOs;
        }
        List<StructureReportEntity> objectList = BaseMongoTemplate.getUserReportMongo().find(Query.query(Criteria.where(ACCOUNT_ID).is(AppContext.getAccountId())), getEntityClass(), userTable);
        List<StructureReportDTO> structureReportDTOs = ObjectUtils.convert(objectList, getDTOClass());
        return structureReportDTOs;
    }

    @Override
    public List<AccountReportDTO> getAccountReport(int Sorted, String fieldName) {
        Sort sort;
        if (Sorted == 0) {
            sort = new Sort(Sort.Direction.ASC, fieldName);
        } else {
            sort = new Sort(Sort.Direction.DESC, fieldName);
        }
        List<Date> dates = DateUtils.getsLatestAnyDays("yyyy-MM-dd", 2).get(KEY_DATE);
        List<AccountReportEntity> reportEntities = BaseMongoTemplate.getUserReportMongo().find(Query.query(Criteria.where("date").lte(dates.get(0)).gte(dates.get(1)).and(ACCOUNT_ID).is(AppContext.getAccountId())).with(sort), AccountReportEntity.class, TBL_ACCOUNT_REPORT);

        List<AccountReportDTO> accountReportDTOs = ObjectUtils.convert(reportEntities, AccountReportDTO.class);

        return accountReportDTOs;
    }

    @Override
    public long getAccountCount() {
        long account_report = BaseMongoTemplate.getUserReportMongo().count(Query.query(Criteria.where("acid").is(AppContext.getAccountId())), TBL_ACCOUNT_REPORT);
        return account_report;
    }

    public List<AccountReportDTO> getAccountReport(Date startDate, Date endDate) {
        List<AccountReportEntity> reportResponses = BaseMongoTemplate.getUserReportMongo().find(Query.query(Criteria.where("date").gte(startDate).lte(endDate).and(ACCOUNT_ID).is(AppContext.getAccountId())), AccountReportEntity.class, TBL_ACCOUNT_REPORT);

        List<AccountReportDTO> accountReportDTOs = ObjectUtils.convert(reportResponses, AccountReportDTO.class);
        return accountReportDTOs;
    }

    @Override
    public List<StructureReportDTO> getKeywordReport(Long[] id, String table) {
        List<StructureReportEntity> entityList = BaseMongoTemplate.getUserReportMongo().find(new Query(Criteria.where(KEYWORD_ID).in(id).and(ACCOUNT_ID).is(AppContext.getAccountId())), getEntityClass(), table);
        List<StructureReportDTO> structureReportDTOs = ObjectUtils.convert(entityList, getDTOClass());
        return structureReportDTOs;
    }


    @Override
    public Class<StructureReportEntity> getEntityClass() {
        return StructureReportEntity.class;
    }

    @Override
    public Class<StructureReportDTO> getDTOClass() {
        return StructureReportDTO.class;
    }
}
