package com.perfect.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Created by SubDong on 2014/8/21.
 */
public class AccountReportDTO extends AccountIdDTO implements Comparable<AccountReportDTO>{
    @Id
    private String id;

    @Field(value = "acna")
    private String accountName;

    @Field(value = "date")
    private Date date;

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

    private long count;

    private String dateRep;

    private String OrderBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getDateRep() {
        return dateRep;
    }

    public void setDateRep(String dateRep) {
        this.dateRep = dateRep;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(String orderBy) {
        OrderBy = orderBy;
    }

    @Override
    public int compareTo(AccountReportDTO o) {
        switch (o.getOrderBy()){
            case "1":
                return this.getDate().compareTo(o.getDate());
            case "2":
                return this.getPcImpression().compareTo(o.getPcImpression());
            case "-2":
                return o.getPcImpression().compareTo(this.getPcImpression());
            case "3":
                return this.getPcClick().compareTo(o.getPcClick());
            case "-3":
                return o.getPcClick().compareTo(this.getPcClick());
            case "4":
                return this.getPcCost().compareTo(o.getPcCost());
            case "-4":
                return o.getPcCost().compareTo(this.getPcCost());
            case "5":
                return this.getPcCpc().compareTo(o.getPcCpc());
            case "-5":
                return o.getPcCpc().compareTo(this.getPcCpc());
            case "6":
                return this.getPcCtr().compareTo(o.getPcCtr());
            case "-6":
                return o.getPcCtr().compareTo(this.getPcCtr());
            case "7":
                return this.getPcConversion().compareTo(o.getPcConversion());
            case "-7":
                return o.getPcConversion().compareTo(this.getPcConversion());
            default:
                return o.getDate().compareTo(this.getDate());
        }
    }
}
