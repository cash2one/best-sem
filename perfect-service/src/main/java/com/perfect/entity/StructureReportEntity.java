package com.perfect.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import static com.perfect.mongodb.utils.EntityConstants.*;
/**
 * Created by SubDong on 2014/8/12.
 */
public class StructureReportEntity implements Comparable<StructureReportEntity>{
    @Id
    private String id;

    private String date; //时间

    @Field(value = ADGROUP_ID)
    private Long adgroupId;

    @Field(value = KEYWORD_ID)
    private Long keywordId;  //关键词ID

    @Field(value = CREATIVE_ID)
    private Long creativeId;    //创意ID

    @Field(value = REGION_ID)
    private Long regionId; //地域ID

    @Field(value = "agna")
    private String adgroupName; //单元

    @Field(value = "cpna")
    private String campaignName; //计划

    @Field(value = "kwna")
    private String keywordName; //关键字

    @Field(value = "crtl")
    private String creativeTitle; //创意标题

    @Field(value = "des1")
    private String description1;//创意内容1

    @Field(value = "des2")
    private String description2;//创意内容2

    @Field(value = "rgna")
    private String regionName; //地域名称

    @Field(value = "pcis")
    private Integer pcImpression;     //PC展现次数

    @Field(value = "pccli")
    private Integer pcClick;      //PC点击次数

    @Field(value = "pcctr")
    private Double pcCtr;     //PC点击率=点击次数/展现次数

    @Field(value = "pccost")
    private Double pcCost;        //PC消费

    @Field(value = "pccpc")
    private Double pcCpc;     //PC平均点击价格=消费/点击次数

    @Field(value = "pccpm")
    private Double pcCpm;       //PC千次展现消费

    @Field(value = "pccs")
    private Double pcConversion;      //PC转化

    @Field(value = "mis")
    private Integer mobileImpression;

    @Field(value = "mcli")
    private Integer mobileClick;

    @Field(value = "mctr")
    private Double mobileCtr;

    @Field(value = "mcost")
    private Double mobileCost;

    @Field(value = "mcpc")
    private Double mobileCpc;

    @Field(value = "mcpm")
    private Double mobileCpm;

    @Field(value = "mcs")
    private Double mobileConversion;

    private String orderBy;

    private int terminal;

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getAdgroupId() {
        return adgroupId;
    }

    public void setAdgroupId(Long adgroupId) {
        this.adgroupId = adgroupId;
    }

    public String getAdgroupName() {
        return adgroupName;
    }

    public void setAdgroupName(String adgroupName) {
        this.adgroupName = adgroupName;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public Long getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }

    public Long getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(Long creativeId) {
        this.creativeId = creativeId;
    }

    public String getCreativeTitle() {
        return creativeTitle;
    }

    public void setCreativeTitle(String creativeTitle) {
        this.creativeTitle = creativeTitle;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getPcImpression() {
        return pcImpression;
    }

    public void setPcImpression(Integer pcImpression) {
        this.pcImpression = pcImpression;
    }

    public Integer getPcClick() {
        return pcClick;
    }

    public void setPcClick(Integer pcClick) {
        this.pcClick = pcClick;
    }

    public Double getPcCtr() {
        return pcCtr;
    }

    public void setPcCtr(Double pcCtr) {
        this.pcCtr = pcCtr;
    }

    public Double getPcCost() {
        return pcCost;
    }

    public void setPcCost(Double pcCost) {
        this.pcCost = pcCost;
    }

    public Double getPcCpc() {
        return pcCpc;
    }

    public void setPcCpc(Double pcCpc) {
        this.pcCpc = pcCpc;
    }

    public Double getPcCpm() {
        return pcCpm;
    }

    public void setPcCpm(Double pcCpm) {
        this.pcCpm = pcCpm;
    }

    public Double getPcConversion() {
        return pcConversion;
    }

    public void setPcConversion(Double pcConversion) {
        this.pcConversion = pcConversion;
    }

    public Integer getMobileImpression() {
        return mobileImpression;
    }

    public void setMobileImpression(Integer mobileImpression) {
        this.mobileImpression = mobileImpression;
    }

    public Integer getMobileClick() {
        return mobileClick;
    }

    public void setMobileClick(Integer mobileClick) {
        this.mobileClick = mobileClick;
    }

    public Double getMobileCtr() {
        return mobileCtr;
    }

    public void setMobileCtr(Double mobileCtr) {
        this.mobileCtr = mobileCtr;
    }

    public Double getMobileCost() {
        return mobileCost;
    }

    public void setMobileCost(Double mobileCost) {
        this.mobileCost = mobileCost;
    }

    public Double getMobileCpc() {
        return mobileCpc;
    }

    public void setMobileCpc(Double mobileCpc) {
        this.mobileCpc = mobileCpc;
    }

    public Double getMobileCpm() {
        return mobileCpm;
    }

    public void setMobileCpm(Double mobileCpm) {
        this.mobileCpm = mobileCpm;
    }

    public Double getMobileConversion() {
        return mobileConversion;
    }

    public void setMobileConversion(Double mobileConversion) {
        this.mobileConversion = mobileConversion;
    }

    @Override
    public String toString() {
        return "StructureReportEntity{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", adgroupId=" + adgroupId +
                ", adgroupName='" + adgroupName + '\'' +
                ", campaignName='" + campaignName + '\'' +
                ", keywordId=" + keywordId +
                ", keywordName='" + keywordName + '\'' +
                ", creativeId=" + creativeId +
                ", creativeTitle='" + creativeTitle + '\'' +
                ", description1='" + description1 + '\'' +
                ", description2='" + description2 + '\'' +
                ", regionId=" + regionId +
                ", regionName='" + regionName + '\'' +
                ", pcImpression=" + pcImpression +
                ", pcClick=" + pcClick +
                ", pcCtr=" + pcCtr +
                ", pcCost=" + pcCost +
                ", pcCpc=" + pcCpc +
                ", pcCpm=" + pcCpm +
                ", pcConversion=" + pcConversion +
                ", mobileImpression=" + mobileImpression +
                ", mobileClick=" + mobileClick +
                ", mobileCtr=" + mobileCtr +
                ", mobileCost=" + mobileCost +
                ", mobileCpc=" + mobileCpc +
                ", mobileCpm=" + mobileCpm +
                ", mobileConversion=" + mobileConversion +
                '}';
    }

    @Override
    public int compareTo(StructureReportEntity o) {
        switch (o.getOrderBy()){
            //展现排序
            case "1":
                if(o.getTerminal() == 2){
                    return this.getMobileImpression().compareTo(o.getMobileImpression());
                }else{
                    return this.getPcImpression().compareTo(o.getPcImpression());
                }
                //点击排序
            case "2":
                if(o.getTerminal() == 2){
                    return this.getMobileClick().compareTo(o.getMobileClick());
                }else{
                    return this.getPcClick().compareTo(o.getPcClick());
                }

            case "-2":
                if(o.getTerminal() == 2){
                    return o.getMobileClick().compareTo(this.getMobileClick());
                }else{
                    return o.getPcClick().compareTo(this.getPcClick());
                }
            //消费排序
            case "3":
                if(o.getTerminal() == 2){
                    return this.getMobileCost().compareTo(o.getMobileCost());
                }else{
                    return this.getPcCost().compareTo(o.getPcCost());
                }
            case "-3":
                if(o.getTerminal() == 2){
                    return o.getMobileCost().compareTo(this.getMobileCost());
                }else{
                    return o.getPcCost().compareTo(this.getPcCost());
                }
            //平均点击价格排序
            case "4":
                if(o.getTerminal() == 2){
                    return this.getMobileCpc().compareTo(o.getMobileCpc());
                }else{
                    return this.getPcCpc().compareTo(o.getPcCpc());
                }
            case "-4":
                if(o.getTerminal() == 2){
                    return o.getMobileCpc().compareTo(this.getMobileCpc());
                }else{
                    return o.getPcCpc().compareTo(this.getPcCpc());
                }
                //点击率排序
            case "5":
                if(o.getTerminal() == 2){
                    return this.getMobileCtr().compareTo(o.getMobileCtr());
                }else{
                    return this.getPcCtr().compareTo(o.getPcCtr());
                }
            case "-5":
                if(o.getTerminal() == 2){
                    return o.getMobileCtr().compareTo(this.getMobileCtr());
                }else{
                    return o.getPcCtr().compareTo(this.getPcCtr());
                }
                //转化排序
            case "6":
                if(o.getTerminal() == 2){
                    return this.getMobileConversion().compareTo(o.getMobileConversion());
                }else{
                    return this.getPcConversion().compareTo(o.getPcConversion());
                }
            case "-6":
                if(o.getTerminal() == 2){
                    return o.getMobileConversion().compareTo(this.getMobileConversion());
                }else{
                    return o.getPcConversion().compareTo(this.getPcConversion());
                }
                //单元排序
            case "7":
                    return this.getAdgroupId().compareTo(o.getAdgroupId());
            case "-7":
                    return o.getAdgroupId().compareTo(this.getAdgroupId());
            //计划排序
            case "8":
                    return this.getCampaignName().compareTo(o.getCampaignName());
            case "-8":
                    return o.getCampaignName().compareTo(this.getCampaignName());
            //关键字排序
            case "9":
                    return this.getKeywordName().compareTo(o.getKeywordName());
            case "-9":
                    return o.getKeywordName().compareTo(this.getKeywordName());
            //地域排序
            case "10":
                return this.getRegionId().compareTo(o.getRegionId());
            case "-10":
                return o.getRegionId().compareTo(this.getRegionId());
            default:
                //默认展现排序
                if(o.getTerminal() == 2){
                    return o.getMobileImpression().compareTo(this.getMobileImpression());
                }else{
                    return o.getPcImpression().compareTo(this.getPcImpression());
                }
        }
    }
}
