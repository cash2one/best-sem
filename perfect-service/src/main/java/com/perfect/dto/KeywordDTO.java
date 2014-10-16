package com.perfect.dto;

import com.perfect.entity.KeywordEntity;

import java.io.Serializable;

/**
 * Created by john on 2014/9/2.
 */
public class KeywordDTO implements Serializable{

    private String campaignName;

    private Long campaignId;

    private String adgroupName;

    private KeywordEntity object;

    private long quality;//计算机质量度

    private long mobileQuality;//移动端质量度\

    private Long monitorId;

    private Long folderId;

    private String folderName;

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getAdgroupName() {
        return adgroupName;
    }

    public void setAdgroupName(String adgroupName) {
        this.adgroupName = adgroupName;
    }

    public KeywordEntity getObject() {
        return object;
    }

    public void setObject(KeywordEntity object) {
        this.object = object;
    }

    public long getQuality() {
        return quality;
    }

    public void setQuality(long quality) {
        this.quality = quality;
    }

    public long getMobileQuality() {
        return mobileQuality;
    }

    public void setMobileQuality(long mobileQuality) {
        this.mobileQuality = mobileQuality;
    }

    public Long getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Long monitorId) {
        this.monitorId = monitorId;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
