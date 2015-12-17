package com.perfect.service.impl;

import com.perfect.dao.account.AccountRegisterDAO;
import com.perfect.dto.sys.ModuleAccountInfoDTO;
import com.perfect.dto.sys.SystemUserDTO;
import com.perfect.dto.baidu.BaiduAccountInfoDTO;
import com.perfect.dto.sys.SystemUserModuleDTO;
import com.perfect.entity.sys.ModuleAccountInfoEntity;
import com.perfect.entity.sys.SystemUserEntity;
import com.perfect.entity.sys.SystemUserModuleEntity;
import com.perfect.param.RegisterParam;
import com.perfect.service.AccountRegisterService;
import com.perfect.utils.MD5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SubDong on 2014/9/30.
 */
@Service("accountRegisterService")
public class AccountRegisterServiceImpl implements AccountRegisterService {

    @Resource
    private AccountRegisterDAO accountRegisterDAO;

    @Override
    public int addAccount(String account, String pwd, String company, String email) {

        SystemUserDTO systemUserDTO = new SystemUserDTO();
        MD5.Builder md5Builder = new MD5.Builder();
        MD5 md5 = md5Builder.password(pwd).salt(account).build();
        SystemUserDTO administrator = accountRegisterDAO.getAccount("administrator");
        systemUserDTO.setAccess(2);
        systemUserDTO.setBaiduAccounts(new ArrayList<BaiduAccountInfoDTO>());
        systemUserDTO.setUserName(account);
        systemUserDTO.setPassword(md5.getMD5());
        systemUserDTO.setCompanyName(company);
        systemUserDTO.setEmail(email);
        systemUserDTO.setState(0);
        systemUserDTO.setImg(administrator.getImg());

        systemUserDTO.setAccountState(1);
        int returnState;
        SystemUserDTO user = accountRegisterDAO.getAccount(account);
        if (user == null) {
            accountRegisterDAO.addAccount(systemUserDTO);
            returnState = 1;
        } else {
            returnState = -1;
        }
        return returnState;
    }

    @Override
    public int addAccount(RegisterParam registerParam) {
        int returnState = -1;
        if (registerParam != null) {
            SystemUserDTO systemUserDTO = new SystemUserDTO();
            //公司名称
            systemUserDTO.setCompanyName(registerParam.getCompanyname());
            //帐号
            systemUserDTO.setUserName(registerParam.getUsername());
            MD5.Builder md5Builder = new MD5.Builder();
            MD5 md5 = md5Builder.password(registerParam.getPassword()).salt(registerParam.getUsername()).build();
            //密码
            systemUserDTO.setPassword(md5.getMD5());
            //邮箱
            systemUserDTO.setEmail(registerParam.getEmail());
            //联系人
            systemUserDTO.setContactName(registerParam.getContacts());
            //办公电话
            systemUserDTO.setTelephone("");
            //移动电话
            systemUserDTO.setMobilephone(registerParam.getContactsPhone());
            //通讯地址
            systemUserDTO.setAddress(registerParam.getMailinAddress());
            //审核状态
            systemUserDTO.setState(0);
            systemUserDTO.setAccess(2);
            //注册时间
            systemUserDTO.setCtime(new Date().getTime());
            //是否付费用户
            systemUserDTO.setPayed(registerParam.getAccountType() == 2);

            //开通平台
            String[] dd = registerParam.getOpenPlatform().split(",");
            List<SystemUserModuleDTO> entities = new ArrayList<>();
            for (String s : dd) {
                SystemUserModuleDTO systemUserModuleDTO = new SystemUserModuleDTO();
                systemUserModuleDTO.setModuleId(s);
                systemUserModuleDTO.setIsPayed(false);
                systemUserModuleDTO.setEnabled(false);

                //百度账户处理
                List<ModuleAccountInfoDTO> baiduList = new ArrayList<>();
                ModuleAccountInfoDTO baiduAccountInfo = new ModuleAccountInfoDTO();
                baiduAccountInfo.setBaiduUserName(registerParam.getPhoenixNestUser());
                baiduAccountInfo.setBaiduPassword(registerParam.getPhoenixNestPassword());
                baiduAccountInfo.setBestRegDomain(registerParam.getUrlAddress());
                baiduList.add(baiduAccountInfo);
                //设置百度帐号信息
                systemUserModuleDTO.setAccounts(baiduList);
                systemUserModuleDTO.setStartTime(new Date().getTime());
                entities.add(systemUserModuleDTO);
            }
            systemUserDTO.setModuleDTOList(entities);

            SystemUserDTO user = accountRegisterDAO.getAccount(registerParam.getUsername());

            if (user == null) {
                //accountRegisterDAO.regAccount(systemUserDTO);
                returnState = 1;
            } else {
                returnState = -1;
            }
        }
        return returnState;
    }
}
