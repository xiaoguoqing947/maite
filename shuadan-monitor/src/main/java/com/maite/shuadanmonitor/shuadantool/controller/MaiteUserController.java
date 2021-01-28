package com.maite.shuadanmonitor.shuadantool.controller;


import com.maite.shuadanmonitor.shuadantool.service.IMaiteUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xgq
 * @since 2021-01-20
 */
@Controller
@RequestMapping("/api/user")
public class MaiteUserController {

    private static final Logger log = LoggerFactory.getLogger(MaiteUserController.class);

    @Autowired
    IMaiteUserService maiteUserService;


    @ResponseBody
    @GetMapping("/queryUserNameList")
    public List<String> queryUserNameList(){
        List<String> userNameList = null;
        userNameList = maiteUserService.queryUserNameList();
        return userNameList;
    }
}
