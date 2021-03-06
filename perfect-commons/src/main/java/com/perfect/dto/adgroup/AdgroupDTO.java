package com.perfect.dto.adgroup;

import com.perfect.dto.account.AccountIdDTO;
import com.perfect.dto.baidu.OptTypeDTO;

import java.util.List;

/**
 * Created by yousheng on 14/11/20.
 */
public class AdgroupDTO extends AccountIdDTO {
    private Long adgroupId;
    private Long campaignId;
    private String campaignObjId;
    private String adgroupName;
    private Double maxPrice;
    private List<String> negativeWords;
    private List<String> exactNegativeWords;
    private String reserved;
    private Boolean pause;
    private Integer status;
    private OptTypeDTO opt;
    private Integer localStatus;
    private String campaignName;

    public Long getAdgroupId() {
        return adgroupId;
    }

    public void setAdgroupId(Long adgroupId) {
        this.adgroupId = adgroupId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignObjId() {
        return campaignObjId;
    }

    public void setCampaignObjId(String campaignObjId) {
        this.campaignObjId = campaignObjId;
    }

    public String getAdgroupName() {
        return adgroupName;
    }

    public void setAdgroupName(String adgroupName) {
        this.adgroupName = adgroupName;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<String> getNegativeWords() {
        return negativeWords;
    }

    public void setNegativeWords(List<String> negativeWords) {
        this.negativeWords = negativeWords;
    }

    public List<String> getExactNegativeWords() {
        return exactNegativeWords;
    }

    public void setExactNegativeWords(List<String> exactNegativeWords) {
        this.exactNegativeWords = exactNegativeWords;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public Boolean getPause() {
        return pause;
    }

    public void setPause(Boolean pause) {
        this.pause = pause;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getLocalStatus() {
        return localStatus;
    }

    public void setLocalStatus(Integer localStatus) {
        this.localStatus = localStatus;
    }

    public OptTypeDTO getOpt() {
        return opt;
    }

    public void setOpt(OptTypeDTO opt) {
        this.opt = opt;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }
}
