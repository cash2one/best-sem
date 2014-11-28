package com.perfect.service.impl;

import com.perfect.dao.account.AccountRegisterDAO;
import com.perfect.dto.SystemUserDTO;
import com.perfect.dto.baidu.BaiduAccountInfoDTO;
import com.perfect.service.AccountRegisterService;
import com.perfect.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by SubDong on 2014/9/30.
 */
@Service("accountRegisterService")
public class AccountRegisterServiceImpl implements AccountRegisterService {

    @Resource
    private AccountRegisterDAO accountRegisterDAO;

    @Override
    public int addAccount(String account, String pwd, String company, String email) {

        SystemUserDTO userEntity = new SystemUserDTO();
        MD5Utils.Builder md5Builder = new MD5Utils.Builder();
        MD5Utils md5 = md5Builder.password(pwd).salt(account).build();

        userEntity.setAccess(2);
        userEntity.setBaiduAccountInfoDTOs(new ArrayList<BaiduAccountInfoDTO>());
        userEntity.setUserName(account);
        userEntity.setPassword(md5.getMD5());
        userEntity.setCompanyName(company);
        userEntity.setEmail(email);
        userEntity.setState(0);
        int returnState;
        SystemUserDTO user = accountRegisterDAO.getAccount(account);
        if (user == null) {
            accountRegisterDAO.addAccount(userEntity);
            returnState = 1;
        } else {
            returnState = -1;
        }
        return returnState;
    }
}
