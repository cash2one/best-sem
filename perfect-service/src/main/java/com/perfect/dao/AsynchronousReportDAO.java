package com.perfect.dao;

/**
 * Created by baizz on 2014-08-07.
 */
public interface AsynchronousReportDAO {

    void getAccountReportData(String dateStr);

    void getCampaignReportData(String dateStr);

    void getAdgroupReportData(String dateStr);

    void getCreativeReportData(String dateStr);

    void getKeywordReportData(String dateStr);

    void getRegionReportData(String dateStr);
}