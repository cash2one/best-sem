package com.perfect.usercenter.controller;

import com.perfect.commons.web.JsonResultMaps;
import com.perfect.dto.sys.SystemModuleDTO;
import com.perfect.param.RegisterParam;
import com.perfect.service.AccountRegisterService;
import com.perfect.service.SystemModuleService;
import com.perfect.utils.JsonViews;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by subdong on 15-12-16.
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/userCenter")
public class RegisterController {

    @Resource
    private AccountRegisterService accountRegisterService;

    @Resource
    private SystemModuleService systemModuleService;
    /**
     * 平台查询
     */
    @RequestMapping(value = "/getPlatform", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getPlatform(HttpServletResponse response, HttpServletRequest request) {
        List<SystemModuleDTO> systemModuleDTOs = systemModuleService.list();

        if (systemModuleDTOs == null || systemModuleDTOs.isEmpty()) {
            return JsonViews.generateSuccessNoData();
        }

        return JsonViews.generate(JsonResultMaps.successMap(systemModuleDTOs));

    }

    /**
     * 注册帐号
     *
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView registerData(ModelMap model, HttpServletResponse response, HttpServletRequest request,
                                     @ModelAttribute RegisterParam registerParam) {

        int flag = accountRegisterService.addAccount(registerParam);
        model.addAttribute("state", 1);
        return new ModelAndView("loginOrReg/register", model);
    }

    private ModelAndView getModel(ModelMap model){
        model.addAttribute("state", -1);
        return new ModelAndView("loginOrReg/register", model);
    }
}
