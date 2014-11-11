package com.perfect.service.impl;

import com.perfect.dao.CensusDAO;
import com.perfect.dao.MongoCrudRepository;
import com.perfect.entity.CensusEntity;
import com.perfect.mongodb.base.AbstractUserBaseDAOImpl;
import com.perfect.mongodb.utils.Pager;
import com.perfect.service.CensusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by XiaoWei on 2014/11/11.
 */
@Service("censusService")
public class CensusServiceImpl extends AbstractUserBaseDAOImpl<CensusEntity,Long> implements CensusService {
    @Resource private CensusDAO censusDAO;
    @Override
    public Class<CensusEntity> getEntityClass() {
        return null;
    }

    @Override
    public Pager findByPager(int start, int pageSize, Map<String, Object> q, int orderBy) {
        return null;
    }

    @Override
    public String saveParams(String[] osAnBrowser) {
        String uuid=osAnBrowser[0];
        String system=osAnBrowser[1];
        String browser=osAnBrowser[2];
        String resolution=osAnBrowser[3];
        boolean supportCookie= Boolean.parseBoolean(osAnBrowser[4]);
        boolean supportJava= Boolean.parseBoolean(osAnBrowser[5]);
        String bit=osAnBrowser[6];
        String flash=osAnBrowser[7];
        String time=osAnBrowser[8];
        String lastPage=osAnBrowser[9];
        String ip=osAnBrowser[10];
        String area=osAnBrowser[11];
        Integer operate= Integer.valueOf(osAnBrowser[12]);
        CensusEntity censusEntity=new CensusEntity();
        censusEntity.setUuid(uuid);
        censusEntity.setSystem(system);
        censusEntity.setBrowser(browser);
        censusEntity.setResolution(resolution);
        censusEntity.setSupportCookie(supportCookie);
        censusEntity.setSupportJava(supportJava);
        censusEntity.setBit(bit);
        censusEntity.setFlash(flash);
        censusEntity.setTime(time);
        censusEntity.setLastPage(lastPage);
        censusEntity.setIp(ip);
        censusEntity.setArea(area);
        censusEntity.setOperate(operate);
        censusDAO.saveParams(censusEntity);
        return censusEntity.getId();
    }
}
