package com.perfect.db.mongodb.impl;

import com.perfect.api.baidu.BaiduService;
import com.perfect.autosdk.core.ServiceFactory;
import com.perfect.autosdk.exception.ApiException;
import com.perfect.dao.SystemUserDAO;
import com.perfect.db.mongodb.base.AbstractSysBaseDAOImpl;
import com.perfect.dto.SystemUserDTO;
import com.perfect.dto.baidu.BaiduAccountInfoDTO;
import com.perfect.entity.BaiduAccountInfoEntity;
import com.perfect.entity.SystemUserEntity;
import com.perfect.utils.Pager;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vbzer_000 on 2014-6-19.
 * 2014-11-24 refactor
 */
@Repository("systemUserDAO")
public class SystemUserDAOImpl extends AbstractSysBaseDAOImpl<SystemUserDTO, String> implements SystemUserDAO {

    @Resource
    private BaiduService baiduService;

    @Override
    public void addBaiduAccount(List<BaiduAccountInfoDTO> list, String currSystemUserName) {
        SystemUserDTO currSystemUserDTO = findByUserName(currSystemUserName);
        List<BaiduAccountInfoDTO> _list = currSystemUserDTO.getBaiduAccountInfoDTOs();
        if (_list == null) {
            _list = new ArrayList<>();
        }
        _list.addAll(list);
        getSysMongoTemplate().updateFirst(Query.query(Criteria.where("userName").is(currSystemUserName)), Update.update("baiduAccountInfos", _list), "sys_user");
    }

    @Override
    public void updateAccount(String userName) {
        SystemUserDTO systemUserDTO = findByUserName(userName);
        List<BaiduAccountInfoDTO> list = systemUserDTO.getBaiduAccountInfoDTOs();
        try {
            for (BaiduAccountInfoDTO dto : list) {
                ServiceFactory sf = ServiceFactory.getInstance(dto.getBaiduUserName(), dto.getBaiduPassword(), dto.getToken(), null);
                baiduService.init(sf);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SystemUserDTO findByAid(long aid) {
        Query query = Query.query(Criteria.where("bdAccounts._id").is(aid));
        SystemUserDTO systemUserDTO = new SystemUserDTO();
        BeanUtils.copyProperties(getSysMongoTemplate().findOne(query, getSystemUserEntityClass()), systemUserDTO);
        return systemUserDTO;
    }

    @Override
    public void insertAccountInfo(String user, BaiduAccountInfoDTO baiduAccountInfoDTO) {

        SystemUserDTO systemUserDTO = findByUserName(user);
        if (systemUserDTO.getBaiduAccountInfoDTOs().isEmpty())
            baiduAccountInfoDTO.setDfault(true);

        BaiduAccountInfoEntity baiduAccountInfoEntity = new BaiduAccountInfoEntity();
        BeanUtils.copyProperties(baiduAccountInfoDTO, baiduAccountInfoEntity);
        Update update = new Update();
        update.addToSet("bdAccounts", baiduAccountInfoEntity);
        getSysMongoTemplate().upsert(Query.query(Criteria.where("userName").is(user)), update, getSystemUserEntityClass());
    }

    @Override
    public void removeAccountInfo(Long id) {
        Update update = new Update();
        update.unset("bdAccounts");
        getSysMongoTemplate().updateFirst(Query.query(Criteria.where("bdAccounts._id").is(id)), update, getSystemUserEntityClass());
    }

    @Override
    public SystemUserDTO findByUserName(String userName) {
        SystemUserDTO user = new SystemUserDTO();
        BeanUtils.copyProperties(
                getSysMongoTemplate().findOne(
                        Query.query(Criteria.where("userName").is(userName)),
                        getSystemUserEntityClass(),
                        "sys_user"),
                user);
        return user;
    }

    @Override
    public Pager findByPager(int start, int pageSize, Map<String, Object> q, int orderBy) {
        return null;
    }

    @Override
    public Class<SystemUserDTO> getEntityClass() {
        return SystemUserDTO.class;
    }

    private Class<SystemUserEntity> getSystemUserEntityClass() {
        return SystemUserEntity.class;
    }

    @Override
    public <S extends SystemUserDTO> S save(S entity) {
        SystemUserEntity systemUserEntity = new SystemUserEntity();
        BeanUtils.copyProperties(entity, systemUserEntity);
        getSysMongoTemplate().save(systemUserEntity);
        BeanUtils.copyProperties(systemUserEntity, entity);
        return entity;
    }
}
