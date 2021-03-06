package com.perfect.db.mongodb.impl;

import com.perfect.dao.account.AccountRegisterDAO;
import com.perfect.db.mongodb.base.AbstractSysBaseDAOImpl;
import com.perfect.dto.SystemUserDTO;
import com.perfect.entity.sys.SystemUserEntity;
import com.perfect.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by SubDong on 2014/9/30.
 */
@Repository("accountRegisterDao")
public class AccountRegisterDaoImpl extends AbstractSysBaseDAOImpl<SystemUserDTO, Long> implements AccountRegisterDAO {
    @Override
    public void addAccount(SystemUserDTO systemUserDTO) {

        SystemUserEntity sys_user = new SystemUserEntity();
        BeanUtils.copyProperties(systemUserDTO, sys_user);

        getSysMongoTemplate().insert(sys_user, "sys_user");
    }

    @Override
    public SystemUserDTO getAccount(String userName) {
        SystemUserEntity user = getSysMongoTemplate().findOne(Query.query(Criteria.where("userName").is(userName)), getEntityClass(), "sys_user");

        SystemUserDTO systemUserDTO = ObjectUtils.convertToObject(user, SystemUserDTO.class);

        return systemUserDTO;
    }


    @Override
    public Class<SystemUserEntity> getEntityClass() {
        return SystemUserEntity.class;
    }

    @Override
    public Class<SystemUserDTO> getDTOClass() {
        return SystemUserDTO.class;
    }
}
