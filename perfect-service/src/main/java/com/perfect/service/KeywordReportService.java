package com.perfect.service;

import com.perfect.dto.keyword.KeywordReportDTO;
import com.perfect.utils.paging.PagerInfo;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by XiaoWei on 2014-9-17.
 * 2014-11-26 refactor
 */
public interface KeywordReportService  {
    PagerInfo findByPagerInfo(Map<String, Object> params);
    void downAccountCSV(OutputStream os,List<KeywordReportDTO> list);
    List<KeywordReportDTO> getAll(Map<String,Object> params);
}
