package com.perfect.mongodb.dao.impl;

import com.perfect.dao.AccountAnalyzeDAO;
import com.perfect.entity.AccountReportEntity;
import com.perfect.entity.KeywordRealTimeDataVOEntity;
import com.perfect.mongodb.base.AbstractUserBaseDAOImpl;
import com.perfect.mongodb.base.BaseMongoTemplate;
import com.perfect.mongodb.utils.Pager;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by baizz on 14-7-25.
 */
@Repository("accountAnalyzeDAO")
public class AccountAnalyzeDAOImpl extends AbstractUserBaseDAOImpl<KeywordRealTimeDataVOEntity, Long> implements AccountAnalyzeDAO {

    @Override
    public Class<KeywordRealTimeDataVOEntity> getEntityClass() {
        return KeywordRealTimeDataVOEntity.class;
    }

    public Pager findByPager(int start, int pageSize, Map<String, Object> q, int orderBy) {
        return null;
    }

    @Override
    public List<KeywordRealTimeDataVOEntity> performance(String userTable) {
        MongoTemplate mongoTemplate = BaseMongoTemplate.getUserReportMongo();
        List<KeywordRealTimeDataVOEntity> list = mongoTemplate.findAll(KeywordRealTimeDataVOEntity.class, userTable);
        return list;
    }

    @Override
    public List<AccountReportEntity> performaneUser(Date startDate, Date endDate, String fieldName, int Sorted, int limit) {
        MongoTemplate mongoTemplate = BaseMongoTemplate.getUserReportMongo();
        Sort sort = null;
        if (Sorted == 0) {
            sort = new Sort(Sort.Direction.ASC, fieldName);
        } else {
            sort = new Sort(Sort.Direction.DESC, fieldName);
        }
        List<AccountReportEntity> list = mongoTemplate.find(Query.query(Criteria.where("date").gte(startDate).lte(endDate)).with(sort).skip(0).limit(limit), AccountReportEntity.class, "account_report");
        return list;
    }

    @Override
    public List<AccountReportEntity> performaneCurve(Date startDate, Date endDate) {
        MongoTemplate mongoTemplate = BaseMongoTemplate.getUserReportMongo();
        List<AccountReportEntity> list = mongoTemplate.find(Query.query(Criteria.where("date").gte(startDate).lte(endDate)).with(new Sort(Sort.Direction.ASC, "date")), AccountReportEntity.class, "account_report");
        return list;
    }
}
