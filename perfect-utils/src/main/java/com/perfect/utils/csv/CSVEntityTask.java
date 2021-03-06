package com.perfect.utils.csv;


import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by XiaoWei on 2014/8/11.
 */
public class CSVEntityTask extends RecursiveTask<List<CSVEntityTask.CSVEntity>> {
    private static final int threshold = 40000;

    private int first;
    private int last;
    private List<CSVEntity> collectionNameList;

    public CSVEntityTask(int first, int last, List<CSVEntity> collectionNameList) {
        this.first = first;
        this.last = last;
        this.collectionNameList = collectionNameList;
    }

    @Override
    protected List<CSVEntity> compute() {
        List<CSVEntity> csvEntity = new LinkedList<>();
        List<CSVEntity> _newCsvEntity = new LinkedList<>();
        if ((last - first) < threshold) {
            HashSet h = new HashSet(collectionNameList);
            collectionNameList.clear();
            collectionNameList.addAll(h);
            csvEntity = collectionNameList;
        } else {
            int middle = (last + first) / 2;
            CSVEntityTask csvEntityTask = new CSVEntityTask(first, middle, collectionNameList);
            CSVEntityTask csvEntityTask2 = new CSVEntityTask(middle, last, collectionNameList);
            invokeAll(csvEntityTask, csvEntityTask2);
            _newCsvEntity.clear();
            _newCsvEntity = mergeMap(csvEntityTask.join(), csvEntityTask2.join());
        }
        return _newCsvEntity;
    }

    private List<CSVEntity> mergeMap(List<CSVEntity> list1, List<CSVEntity> list2) {
        list1.removeAll(list2);
        list1.addAll(list2);
        return list1;
    }

    protected static class CSVEntity implements Serializable {
        Integer lineNumber;//行号，也就是索引
        String account;//账号
        String plan;//计划
        String unitName;//单元名称
        double unitPrice;//单元价格
        String keyword;//关键词
        double outPrice;//出价
        String thisMatchMode;//当前匹配模式
        String quality;//质量度
        double lowestPrice;//最低出价
        String accountId;//账号Id
        String planId;//计划id
        String unitId;//单元id
        String keywordId;//关键词id

        public Integer getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(Integer lineNumber) {
            this.lineNumber = lineNumber;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPlan() {
            return plan;
        }

        public void setPlan(String plan) {
            this.plan = plan;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public double getOutPrice() {
            return outPrice;
        }

        public void setOutPrice(double outPrice) {
            this.outPrice = outPrice;
        }

        public String getThisMatchMode() {
            return thisMatchMode;
        }

        public void setThisMatchMode(String thisMatchMode) {
            this.thisMatchMode = thisMatchMode;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public double getLowestPrice() {
            return lowestPrice;
        }

        public void setLowestPrice(double lowestPrice) {
            this.lowestPrice = lowestPrice;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getPlanId() {
            return planId;
        }

        public void setPlanId(String planId) {
            this.planId = planId;
        }

        public String getUnitId() {
            return unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
        }

        public String getKeywordId() {
            return keywordId;
        }

        public void setKeywordId(String keywordId) {
            this.keywordId = keywordId;
        }
    }
}
