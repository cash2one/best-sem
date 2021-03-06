package com.perfect.entity.campaign;

import com.perfect.commons.constants.MongoEntityConstants;
import com.perfect.entity.ScheduleEntity;
import com.perfect.entity.account.AccountIdEntity;
import com.perfect.entity.baidu.OfflineTimeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by vbzer_000 on 2014-6-26.
 *
 * @description 推广计划实体类
 */
@Document(collection = MongoEntityConstants.TBL_CAMPAIGN)
public class CampaignEntity extends AccountIdEntity {

    @Id
    private String id;

    @Indexed(sparse = true)
    @Field(MongoEntityConstants.CAMPAIGN_ID)
    private Long campaignId;                                    // 百度推广计划ID

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    @Field("name")
    private String campaignName;                                // 推广计划名称

    @Field("bd")
    private Double budget;                                      // 推广计划每日预算

    @Field("rt")
    private List<Integer> regionTarget;                         // 推广地域列表

    @Field("exip")
    private List<String> excludeIp;                             // IP排除列表

    @Field("neg")
    private List<String> negativeWords;                         // 否定关键词列表

    @Field("exneg")
    private List<String> exactNegativeWords;                    // 精确否定关键词列表

    @Field("sd")
    private List<ScheduleEntity> schedule;                      // 暂停推广时段

    @Field("off")
    private List<OfflineTimeEntity> budgetOfflineTime;          // 达到预算下线时间

    @Field("sp")
    private Integer showProb;                                   // 创意展现方式

    @Field("d")
    private Integer device = 0;                                   // 计划的投放设备

    @Field("pr")
    private Double priceRatio;                                  // 计划的无线出价比例

    @Field("p")
    private Boolean pause;                                      // 暂停/启用 推广计划

    @Field("s")
    private Integer status;                                     // 推广计划状态

    @Field("idc")
    private Boolean isDynamicCreative;                          // 启用/暂停 动态创意

    @Field("ls")
    private Integer localStatus;                                // 本地操作状态

    public Integer getLocalStatus() {
        return localStatus;
    }

    public void setLocalStatus(Integer localStatus) {
        this.localStatus = localStatus;
    }

    public boolean setCampaignId(Long aCampaignId) {
        boolean wasSet = false;
        campaignId = aCampaignId;
        wasSet = true;
        return wasSet;
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setCampaignName(String aCampaignName) {
        boolean wasSet = false;
        campaignName = aCampaignName;
        wasSet = true;
        return wasSet;
    }

    public boolean setBudget(Double aBudget) {
        boolean wasSet = false;
        budget = aBudget;
        wasSet = true;
        return wasSet;
    }

    public boolean addRegionTarget(Integer aRegionTarget) {
        boolean wasAdded = false;
        wasAdded = regionTarget.add(aRegionTarget);
        return wasAdded;
    }

    public boolean removeRegionTarget(Integer aRegionTarget) {
        boolean wasRemoved = false;
        wasRemoved = regionTarget.remove(aRegionTarget);
        return wasRemoved;
    }

    public boolean addExcludeIp(String aExcludeIp) {
        boolean wasAdded = false;
        wasAdded = excludeIp.add(aExcludeIp);
        return wasAdded;
    }

    public boolean removeExcludeIp(String aExcludeIp) {
        boolean wasRemoved = false;
        wasRemoved = excludeIp.remove(aExcludeIp);
        return wasRemoved;
    }

    public boolean addNegativeWord(String aNegativeWord) {
        boolean wasAdded = false;
        wasAdded = negativeWords.add(aNegativeWord);
        return wasAdded;
    }

    public boolean removeNegativeWord(String aNegativeWord) {
        boolean wasRemoved = false;
        wasRemoved = negativeWords.remove(aNegativeWord);
        return wasRemoved;
    }

    public boolean addExactNegativeWord(String aExactNegativeWord) {
        boolean wasAdded = false;
        wasAdded = exactNegativeWords.add(aExactNegativeWord);
        return wasAdded;
    }

    public boolean removeExactNegativeWord(String aExactNegativeWord) {
        boolean wasRemoved = false;
        wasRemoved = exactNegativeWords.remove(aExactNegativeWord);
        return wasRemoved;
    }

    public boolean addSchedule(ScheduleEntity aSchedule) {
        boolean wasAdded = false;
        wasAdded = schedule.add(aSchedule);
        return wasAdded;
    }

    public boolean removeSchedule(ScheduleEntity aSchedule) {
        boolean wasRemoved = false;
        wasRemoved = schedule.remove(aSchedule);
        return wasRemoved;
    }

    public boolean addBudgetOfflineTime(OfflineTimeEntity aBudgetOfflineTime) {
        boolean wasAdded = false;
        wasAdded = budgetOfflineTime.add(aBudgetOfflineTime);
        return wasAdded;
    }

    public boolean removeBudgetOfflineTime(OfflineTimeEntity aBudgetOfflineTime) {
        boolean wasRemoved = false;
        wasRemoved = budgetOfflineTime.remove(aBudgetOfflineTime);
        return wasRemoved;
    }

    public boolean setShowProb(Integer aShowProb) {
        boolean wasSet = false;
        showProb = aShowProb;
        wasSet = true;
        return wasSet;
    }

    public boolean setDevice(Integer aDevice) {
        boolean wasSet = false;
        device = aDevice;
        wasSet = true;
        return wasSet;
    }

    public boolean setPriceRatio(Double aPriceRatio) {
        boolean wasSet = false;
        priceRatio = aPriceRatio;
        wasSet = true;
        return wasSet;
    }

    public boolean setPause(Boolean aPause) {
        boolean wasSet = false;
        pause = aPause;
        wasSet = true;
        return wasSet;
    }

    public boolean setStatus(Integer aStatus) {
        boolean wasSet = false;
        status = aStatus;
        wasSet = true;
        return wasSet;
    }

    public boolean setIsDynamicCreative(Boolean aIsDynamicCreative) {
        boolean wasSet = false;
        isDynamicCreative = aIsDynamicCreative;
        wasSet = true;
        return wasSet;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public Double getBudget() {
        return budget;
    }

    public Integer getRegionTarget(int index) {
        Integer aRegionTarget = regionTarget.get(index);
        return aRegionTarget;
    }

    public List<Integer> getRegionTarget() {
        return regionTarget;
    }

    public void setRegionTarget(List<Integer> aregionTarget) {
        regionTarget = aregionTarget;
    }

    public int numberOfRegionTarget() {
        int number = regionTarget.size();
        return number;
    }

    public boolean hasRegionTarget() {
        boolean has = regionTarget.size() > 0;
        return has;
    }

    public int indexOfRegionTarget(Integer aRegionTarget) {
        int index = regionTarget.indexOf(aRegionTarget);
        return index;
    }

    public String getExcludeIp(int index) {
        String aExcludeIp = excludeIp.get(index);
        return aExcludeIp;
    }

    public List<String> getExcludeIp() {
        return excludeIp;
    }

    public void setExcludeIp(List<String> aexcludeIp) {
        excludeIp = aexcludeIp;
    }

    public int numberOfExcludeIp() {
        int number = excludeIp.size();
        return number;
    }

    public boolean hasExcludeIp() {
        boolean has = excludeIp.size() > 0;
        return has;
    }

    public int indexOfExcludeIp(String aExcludeIp) {
        int index = excludeIp.indexOf(aExcludeIp);
        return index;
    }

    public String getNegativeWord(int index) {
        String aNegativeWord = negativeWords.get(index);
        return aNegativeWord;
    }

    public List<String> getNegativeWords() {
        return negativeWords;
    }

    public void setNegativeWords(List<String> anegativeWords) {
        negativeWords = anegativeWords;
    }

    public int numberOfNegativeWords() {
        int number = negativeWords.size();
        return number;
    }

    public boolean hasNegativeWords() {
        boolean has = negativeWords.size() > 0;
        return has;
    }

    public int indexOfNegativeWord(String aNegativeWord) {
        int index = negativeWords.indexOf(aNegativeWord);
        return index;
    }

    public String getExactNegativeWord(int index) {
        String aExactNegativeWord = exactNegativeWords.get(index);
        return aExactNegativeWord;
    }

    public List<String> getExactNegativeWords() {
        return exactNegativeWords;
    }

    public void setExactNegativeWords(List<String> aexactNegativeWords) {
        exactNegativeWords = aexactNegativeWords;
    }

    public int numberOfExactNegativeWords() {
        int number = exactNegativeWords.size();
        return number;
    }

    public boolean hasExactNegativeWords() {
        boolean has = exactNegativeWords.size() > 0;
        return has;
    }

    public int indexOfExactNegativeWord(String aExactNegativeWord) {
        int index = exactNegativeWords.indexOf(aExactNegativeWord);
        return index;
    }

    public ScheduleEntity getSchedule(int index) {
        ScheduleEntity aSchedule = schedule.get(index);
        return aSchedule;
    }

    public List<ScheduleEntity> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleEntity> aschedule) {
        schedule = aschedule;
    }

    public int numberOfSchedule() {
        int number = schedule.size();
        return number;
    }

    public boolean hasSchedule() {
        boolean has = schedule.size() > 0;
        return has;
    }

    public int indexOfSchedule(ScheduleEntity aSchedule) {
        int index = schedule.indexOf(aSchedule);
        return index;
    }

    public OfflineTimeEntity getBudgetOfflineTime(int index) {
        OfflineTimeEntity aBudgetOfflineTime = budgetOfflineTime.get(index);
        return aBudgetOfflineTime;
    }

    public List<OfflineTimeEntity> getBudgetOfflineTime() {
        return budgetOfflineTime;
    }

    public void setBudgetOfflineTime(List<OfflineTimeEntity> abudgetOfflineTime) {
        budgetOfflineTime = abudgetOfflineTime;
    }

    public int numberOfBudgetOfflineTime() {
        int number = budgetOfflineTime.size();
        return number;
    }

    public boolean hasBudgetOfflineTime() {
        boolean has = budgetOfflineTime.size() > 0;
        return has;
    }

    public int indexOfBudgetOfflineTime(OfflineTimeEntity aBudgetOfflineTime) {
        int index = budgetOfflineTime.indexOf(aBudgetOfflineTime);
        return index;
    }

    public Integer getShowProb() {
        return showProb;
    }

    public Integer getDevice() {
        return device;
    }

    public Double getPriceRatio() {
        return priceRatio;
    }

    public Boolean getPause() {
        return pause;
    }

    public Integer getStatus() {
        return status;
    }

    public Boolean getIsDynamicCreative() {
        return isDynamicCreative;
    }

    public void delete() {
    }

    public String toString() {
        String outputString = "";
        return super.toString() + "[" +
                "campaignId" + ":" + getCampaignId() + "," +
                "campaignName" + ":" + getCampaignName() + "," +
                "budget" + ":" + getBudget() + "," +
                "showProb" + ":" + getShowProb() + "," +
                "device" + ":" + getDevice() + "," +
                "priceRatio" + ":" + getPriceRatio() + "," +
                "pause" + ":" + getPause() + "," +
                "status" + ":" + getStatus() + "," +
                "isDynamicCreative" + ":" + getIsDynamicCreative() + "]"
                + outputString;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
