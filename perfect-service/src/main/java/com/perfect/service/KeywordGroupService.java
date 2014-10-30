package com.perfect.service;

import com.perfect.entity.LexiconEntity;
import com.perfect.mongodb.utils.PagerInfo;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by baizz on 2014-08-09.
 */
public interface KeywordGroupService {

    /**
     * <br>-----------------------</br>
     *
     * @param seedWordList
     * @param skip
     * @param limit
     * @param sort
     * @param fieldName
     * @return
     */
    Map<String, Object> getKRResult(List<String> seedWordList, int skip, int limit, int sort, String fieldName);

    /**
     * 从凤巢获取关键词
     *
     * @param seedWordList
     * @param skip
     * @param limit
     * @param reportId
     * @param sort
     * @param fieldName
     * @return
     */
    Map<String, Object> getKeywordFromBaidu(List<String> seedWordList, int skip, int limit, String reportId, int sort, String fieldName);

    /**
     * 从系统词库获取关键词
     *
     * @param trade
     * @param category
     * @param skip
     * @param limit
     * @param status
     * @return
     */
    Map<String, Object> getKeywordFromSystem(String trade, String category, int skip, int limit, int status);

    /**
     * CSV文件下载
     *
     * @param trade
     * @param category
     * @param os
     */
    void downloadCSV(String trade, String category, OutputStream os);

    /**
     * 凤巢CSV文件下载
     *
     * @param seedWordList
     * @param krFileId
     * @param os
     */
    void downloadBaiduCSV(List<String> seedWordList, String krFileId, OutputStream os);

    /**
     * 查询行业词库下的类别
     *
     * @param trade
     * @return
     */
    Map<String, Object> findCategories(String trade);

    /**
     * save
     *
     * @param seedWordList
     * @param krFileId
     */
    void saveKeywordFromBaidu(List<String> seedWordList, String krFileId, String newCampaignName);

    /**
     * save
     *
     * @param trade
     * @param category
     */
    void saveKeywordFromSystem(String trade, String category, String newCampaignName);

    /**
     * @param seedWord
     * @return
     */
    Map<String, Object> getKRbySeedWord(String seedWord);

    /**
     * 查询所有行业名
     * @return
     */
    Map<String,Object> findTr();

    /**
     * 添加数据
     * @param
     */
    int saveTrade(String tr, String cg, String gr, String kw, String url);

    /**
     * 分页查询
     * @param page 当前页码
     * @param limit 每页最大条数
     * @return
     */
    PagerInfo findByPager(int page,int limit);
}
