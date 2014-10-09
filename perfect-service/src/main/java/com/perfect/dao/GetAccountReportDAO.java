package com.perfect.dao;

import com.perfect.autosdk.sms.v3.RealTimeResultType;
import com.perfect.entity.AccountReportEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by john on 2014/8/8.
 */
public interface GetAccountReportDAO {

    /**
     * 得到本地的数据报告（数据来自本地）
     * @param startDate
     * @param endDate
     * @return
     */
    List<AccountReportEntity> getLocalAccountRealData(String userName, Date startDate, Date endDate);

    /**
     * 在百度上实时获取数据报告(数据来自百度)
     * @param startDate
     * @param endDate
     * @return
     */
    List<RealTimeResultType> getAccountRealTimeTypeByDate(Long accountId, String startDate, String endDate);
}
