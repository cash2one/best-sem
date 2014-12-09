package com.perfect.service;

import com.perfect.dto.bidding.BiddingRuleDTO;
import com.perfect.param.BiddingRuleParam;
import com.perfect.utils.paging.PaginationParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by yousheng on 2014/7/30.
 *
 * @author yousheng
 */
public interface BiddingRuleService {

    public void createBiddingRule(BiddingRuleDTO biddingRuleEntity);

    public BiddingRuleDTO findByKeywordId(Long keywordId);

    void updateToNextTime(BiddingRuleDTO biddingRuleEntity, int time);

    public void createRule(BiddingRuleDTO entity);

    public void disableRule(String id);

    public void enableRule(String id);

    public void updateRule(BiddingRuleParam param);

    public List<BiddingRuleDTO> getReadyRule();

    public List<BiddingRuleDTO> getTaskByAccountId(String userName, Long id, long hour);

    void updateRule(List<BiddingRuleDTO> tasks);

    List<BiddingRuleDTO> findRules(Map<String, Object> q, int skip, int limit, String sort, boolean asc);

    List<BiddingRuleDTO> findRules(Map<String, Object> q, String kw, String query, int skip, int limit, String sort, boolean asc);

    List<BiddingRuleDTO> findByKeywordIds(List<Long> ids);

    void remove(Long id);

    void removeByKeywordId(Long id);

    void removeByKeywordIds(List<Long> id);

    boolean exists(Long keywordId);

    @Deprecated
    void updateRank(Collection<BiddingRuleDTO> values);

    boolean setEnable(Long[] ids, boolean ebl);

    List<BiddingRuleDTO> findByNames(String[] split, boolean fullMatch, PaginationParam param, Map<String, Object> queryParams);

    Integer countBiddingRuleDTOfindByNames(String[] split, boolean fullMatch, PaginationParam param, Map<String, Object> queryParams);

    BiddingRuleDTO takeOne(String userName, Long id, long hour);

}
