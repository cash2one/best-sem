package com.perfect.dao.report;

import com.perfect.dao.base.HeyCrudRepository;
import com.perfect.dto.StructureReportDTO;
import com.perfect.dto.account.AccountReportDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by SubDong on 2014/8/6.
 */
public interface BasisReportDAO extends HeyCrudRepository<StructureReportDTO,Long> {
    /**
     * 报告
     *
     * @param userTable 数据表名
     * @return
     */
    public List<StructureReportDTO> getUnitReportDate(String userTable, Long dataId, String dataName);

    /**
     * 获取账户所有数据
     *
     * @return
     */
    public List<AccountReportDTO> getAccountReport(int Sorted, String fieldName);

    /**
     * 得到用户数据条数
     *
     * @return
     */
    public long getAccountCount();

    /**
     * 时间范围获取账户数据
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public List<AccountReportDTO> getAccountReport(Date startDate, Date endDate);


    /**********API接口*********/
    /**
     * 关键字查询
     *
     * @param id
     * @param table
     * @return
     */
    public List<StructureReportDTO> getKeywordReport(Long[] id, String table);

}
