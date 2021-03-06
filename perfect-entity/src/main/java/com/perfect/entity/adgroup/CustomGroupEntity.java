package com.perfect.entity.adgroup;

import com.perfect.commons.constants.MongoEntityConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by XiaoWei on 2014/9/19.
 *
 * @description 自定义推广单元存储实体类
 */
@Document(collection = MongoEntityConstants.TBL_CUSTOMGROUP)
public class CustomGroupEntity {
    @Id
    private String id;                          // MongoDB ID

    @Field("gname")
    private String groupName;                   // 推广单元名称

    @Field(MongoEntityConstants.ACCOUNT_ID)
    private Long accountId;                     // 百度账户ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
