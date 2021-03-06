package com.perfect.nms;

import com.baidu.api.sem.nms.v2.ReportService;
import com.perfect.utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by subdong on 15-7-21.
 */
public class NmsReportIdAPI {

    private final ReportFileUrlTask reportFileUrlTask;

    public NmsReportIdAPI(ReportFileUrlTask reportFileUrlTask) {
        this.reportFileUrlTask = reportFileUrlTask;
    }


    /**
     * 拉取网盟所有报告
     *
     * @param baidAccount
     * @param baidPwd
     * @param token
     */
    public void getAllApi(String baidAccount, String baidPwd, String token, Date[] dates) {
        if (dates[0] == null && dates[1] == null) {
            dates = new Date[]{DateUtils.getYesterday(), DateUtils.getYesterday()};
        }
        DateUtils.getDateInterval(dates[0], dates[1]).forEach(date -> {

            GetReportId example = new GetReportId(baidAccount, baidPwd, token, 0);
            //账户报告
            List<Long> accountId = example.getAccountId();
            Map<String, ReportService> accountIdMap = example.getReportAllId(accountId, 1, 1, date, date);
            if (accountIdMap != null) {
                reportFileUrlTask.add(accountIdMap);
            }


            //计划报告
            List<Long> campaignId = example.getCampaignId();
            Map<String, ReportService> campaignMap = example.getReportAllId(campaignId, 2, 2, date, date);
            if (campaignMap != null) {
                reportFileUrlTask.add(campaignMap);
            }
            //组报告
            List<Long> groupId = example.getGroupByGroupId(campaignId);
            Map<String, ReportService> groupMap = example.getReportAllId(groupId, 3, 3, date, date);
            if (groupMap != null) {
                reportFileUrlTask.add(groupMap);
            }

            //创意报告
            List<Long> adbyGroupId = example.getAdbyGroupId(groupId);
            Map<String, ReportService> adbyGroupMap = example.getReportAllId(adbyGroupId, 4, 4, date, date);
            if (adbyGroupMap != null) {
                reportFileUrlTask.add(adbyGroupMap);
            }
        });

    }

    /**
     * 拉取网盟账户报告
     *
     * @param baidAccount
     * @param baidPwd
     * @param token
     */
    public void getAccountApi(String baidAccount, String baidPwd, String token, Date[] dates) {
        if (dates[0] == null && dates[1] == null) {
            dates = new Date[]{DateUtils.getYesterday(), DateUtils.getYesterday()};
        }
        DateUtils.getDateInterval(dates[0], dates[1]).forEach(date -> {
            GetReportId example = new GetReportId(baidAccount, baidPwd, token, 1);

            //账户报告
            List<Long> accountId = example.getAccountId();
            Map<String, ReportService> accountIdMap = example.getReportAllId(accountId, 1, 1, date, date);
            if (accountIdMap != null) {
                reportFileUrlTask.add(accountIdMap);
            }
        });

    }

    /**
     * 拉取网盟计划报告
     *
     * @param baidAccount
     * @param baidPwd
     * @param token
     */
    public void getCampaignApi(String baidAccount, String baidPwd, String token, Date[] dates) {
        if (dates[0] == null && dates[1] == null) {
            dates = new Date[]{DateUtils.getYesterday(), DateUtils.getYesterday()};
        }
        DateUtils.getDateInterval(dates[0], dates[1]).forEach(date -> {
            GetReportId example = new GetReportId(baidAccount, baidPwd, token, 2);

            //计划报告
            List<Long> campaignId = example.getCampaignId();
            Map<String, ReportService> campaignMap = example.getReportAllId(campaignId, 2, 2, date, date);
            if (campaignMap != null) {
                reportFileUrlTask.add(campaignMap);
            }
        });

    }

    /**
     * 拉取网盟推广组报告
     *
     * @param baidAccount
     * @param baidPwd
     * @param token
     */
    public void getGroupApi(String baidAccount, String baidPwd, String token, Date[] dates) {
        if (dates[0] == null && dates[1] == null) {
            dates = new Date[]{DateUtils.getYesterday(), DateUtils.getYesterday()};
        }
        DateUtils.getDateInterval(dates[0], dates[1]).forEach(date -> {
            GetReportId example = new GetReportId(baidAccount, baidPwd, token, 3);

            //组报告
            List<Long> campaignId = example.getCampaignId();
            List<Long> groupId = example.getGroupByGroupId(campaignId);
            Map<String, ReportService> groupMap = example.getReportAllId(groupId, 3, 3, date, date);
            if (groupMap != null) {
                reportFileUrlTask.add(groupMap);
            }
        });

    }

    /**
     * 拉取网盟创意报告
     *
     * @param baidAccount
     * @param baidPwd
     * @param token
     */
    public void getAdbyGroupApi(String baidAccount, String baidPwd, String token, Date[] dates) {
        if (dates[0] == null && dates[1] == null) {
            dates = new Date[]{DateUtils.getYesterday(), DateUtils.getYesterday()};
        }
        DateUtils.getDateInterval(dates[0], dates[1]).forEach(date -> {
            GetReportId example = new GetReportId(baidAccount, baidPwd, token, 4);

            List<Long> campaignId = example.getCampaignId();
            List<Long> groupId = example.getGroupByGroupId(campaignId);
            List<Long> adbyGroupId = example.getAdbyGroupId(groupId);
            Map<String, ReportService> adbyGroupMap = example.getReportAllId(adbyGroupId, 4, 4, date, date);
            if (adbyGroupMap != null) {
                reportFileUrlTask.add(adbyGroupMap);
            }
        });

    }
}
