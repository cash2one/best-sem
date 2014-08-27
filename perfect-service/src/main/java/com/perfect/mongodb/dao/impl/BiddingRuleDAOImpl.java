package com.perfect.mongodb.dao.impl;

import com.mongodb.WriteResult;
import com.perfect.dao.BiddingRuleDAO;
import com.perfect.dao.SystemUserDAO;
import com.perfect.entity.bidding.BiddingRuleEntity;
import com.perfect.mongodb.base.AbstractUserBaseDAOImpl;
import com.perfect.mongodb.base.BaseMongoTemplate;
import com.perfect.mongodb.utils.Pager;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.perfect.mongodb.utils.FieldConstants.*;
/**
 * Created by yousheng on 2014/8/1.
 *
 * @author yousheng
 */
@Repository("biddingRuleDAO")
public class BiddingRuleDAOImpl extends AbstractUserBaseDAOImpl<BiddingRuleEntity, String> implements BiddingRuleDAO {

    @Resource
    private SystemUserDAO systemUserDAO;

    @Override
    public Pager findByPager(int start, int pageSize, Map<String, Object> q, int orderBy) {
        return null;
    }

    @Override
    public List<BiddingRuleEntity> find(Map<String, Object> params, int skip, int limit) {
        Query query = new Query();
        Criteria criteria = null;
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (criteria == null) {
                criteria = new Criteria(param.getKey());
                criteria.is(param.getValue());
                continue;
            }

            criteria.and(param.getKey()).is(param.getValue());
        }

        query.addCriteria(criteria).skip(skip).limit(limit);

        return getMongoTemplate().find(query, BiddingRuleEntity.class);
    }

    @Override
    public Class<BiddingRuleEntity> getEntityClass() {
        return BiddingRuleEntity.class;
    }


    /**
     * 获取当前所有需要执行的竞价规则
     *
     * @return
     */
//    @Override
//    public List<BiddingRuleEntity> getReadyRule() {
//        Long time = System.currentTimeMillis();
//
//        Query query = Query.query(Criteria.where("next").lte(time));
//
//        List<SystemUserEntity> systemUserEntities = systemUserDAO.findAll();
//
//        List<BiddingRuleEntity> biddingRuleEntityList = new ArrayList<>();
//        for (SystemUserEntity entity : systemUserEntities) {
//            MongoTemplate mongoTemplate = BaseMongoTemplate.getUserMongo(entity.getUserName());
////            biddingRuleEntityList.addAll(mongoTemplate.findAndModify(query, Update.update("next",).class));
//        }
//
//        return biddingRuleEntityList;
//    }
//
//    @Override
//    public boolean disableRule(String id) {
//        MongoTemplate mongoTemplate = getMongoTemplate();
//
//        WriteResult wr = mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(id)), Update.update("ebl", false), BiddingRuleEntity.class);
//
//
//        return wr.getN() > 0;
//    }
//
//    @Override
//    public List<BiddingRuleEntity> getTaskByAccountId(String userName, Long id, int gid) {
//
//        List<BiddingRuleEntity> biddingRuleEntityList = new ArrayList<>();
//
//        long time = System.currentTimeMillis();
//        MongoTemplate mongoTemplate = BaseMongoTemplate.getUserMongo(userName);
//
//        biddingRuleEntityList.addAll(mongoTemplate.find(Query.query(Criteria.where("ebl").is(true).and("gid").is(gid).and("aid").is(id).and("next").lte(time)), BiddingRuleEntity.class));
//
//        return biddingRuleEntityList;
//    }
//
//    @Override
//    public void updateToNextRunTime(List<BiddingRuleEntity> tasks) {
//
//        Map<Long, SystemUserEntity> userMap = new HashMap<>();
//
//        for (BiddingRuleEntity entity : tasks) {
//            long aid = entity.getAccountId();
//            SystemUserEntity systemUserEntity = null;
//            if (userMap.containsKey(aid)) {
//                systemUserEntity = userMap.get(aid);
//            } else {
//                systemUserEntity = systemUserDAO.findByAid(aid);
//                userMap.put(aid, systemUserEntity);
//            }
//
//            MongoTemplate mongoTemplate = BaseMongoTemplate.getUserMongo(systemUserEntity.getUserName());
//            mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(entity.getId())), Update.update("next", entity.getNextTime()), BiddingRuleEntity.class);
//        }
//    }
    private Update getUpdate(BiddingRuleEntity entity) {
        Update update = new Update();

//        if (entity.getNextRunTime() > 0) {
//            update = update.addToSet("next", entity.getNextRunTime());
//        }
//
//        if (entity.getPosition() > 0) {
//            update = update.addToSet("pos", entity.getPosition());
//        }
//
//        if (entity.getInterval() > 0) {
//            update = update.addToSet("intval", entity.getInterval());
//        }
//
//        if (entity.getPositionStrategy() > 0) {
//            update = update.addToSet("pstra", entity.getPositionStrategy());
//        }
//
//        if (entity.getMaxPrice() > 0) {
//            update = update.addToSet("max", entity.getMaxPrice());
//        }
//
//        if (entity.getMinPrice() > 0) {
//            update = update.addToSet("min", entity.getMinPrice());
//        }
//
//        if (entity.getPriority() > 0) {
//            update = update.addToSet("priority", entity.getPriority());
//        }

        return update;
    }

    @Override
    public void createBidding(BiddingRuleEntity biddingRuleEntity) {
        save(biddingRuleEntity);
    }

    @Override
    public List<BiddingRuleEntity> findByCampagainId(long cid, int skip, int limit, String field, Sort.Direction direction) {
        return null;
    }

    @Override
    public void batchCreate(List<BiddingRuleEntity> biddingRuleEntityList) {
        save(biddingRuleEntityList);
    }

    @Override
    public void updateBiddingRule(BiddingRuleEntity biddingRuleEntity) {
        save(biddingRuleEntity);
    }

    @Override
    public BiddingRuleEntity getBiddingRuleByKeywordId(String keywordId) {
        return findOne(keywordId);
    }

    @Override
    public List<BiddingRuleEntity> getReadyRule() {
        return null;
    }

    @Override
    public boolean disableRule(String id) {
        return false;
    }

    @Override
    public int startRule(List<String> id) {
        WriteResult wr = getMongoTemplate().updateMulti(Query.query(Criteria.where("_id").in(id)), Update.update("ebl", true), BiddingRuleEntity.class);
        return wr.getN();
    }

    @Override
    public List<BiddingRuleEntity> getNextRunByGroupId(String userName, Long id) {

        Long time = System.currentTimeMillis();
        Query query = Query.query(Criteria.where("ebl").is(true).and("next").lte(time).and(ACCOUNT_ID).is(id));
        return BaseMongoTemplate.getUserMongo(userName).find(query, getEntityClass());
    }

    @Override
    public void updateToNextRunTime(List<BiddingRuleEntity> tasks) {

    }


    @Override
    public void enableRule(String id) {
        getMongoTemplate().findAndModify(Query.query(Criteria.where(getId()).is(id)), Update.update("ebl", 1), getEntityClass());
    }

    @Override
    public List<BiddingRuleEntity> find(List<Long> ids) {
        return getMongoTemplate().find(Query.query(Criteria.where("kwid").in(ids)), BiddingRuleEntity.class);
    }

    @Override
    public void removeByKeywordId(Long id) {
        getMongoTemplate().remove(Query.query(Criteria.where("kwid").is(id)), getEntityClass());
    }
}
