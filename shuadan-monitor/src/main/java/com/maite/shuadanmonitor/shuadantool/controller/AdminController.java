package com.maite.shuadanmonitor.shuadantool.controller;

import com.maite.shuadanmonitor.shuadantool.entity.MaiteMainContentEntity;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteUser;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteDictionaryService;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteOrderIdService;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteUserService;
import com.maite.shuadanmonitor.utils.ReturnInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：xiao guo qing
 * @date ：2021/1/20 15:15
 * @description：主程序控制类
 */
@Controller
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    //#region 变量【IMaiteUserService，IMaiteOrderIdService，IMaiteDictionaryService】
    IMaiteUserService maiteUserService;
    IMaiteOrderIdService maiteOrderIdService;
    IMaiteDictionaryService maiteDictionaryService;
    //#endregion

    //#region 构造函数
    @Autowired
    public AdminController(IMaiteUserService maiteUserService, IMaiteOrderIdService maiteOrderIdService, IMaiteDictionaryService maiteDictionaryService) {
        this.maiteUserService = maiteUserService;
        this.maiteOrderIdService = maiteOrderIdService;
        this.maiteDictionaryService = maiteDictionaryService;
    }
    //#endregion

    @GetMapping("/")
    public String Index() {
        return "index";
    }

    @GetMapping("/api/admin")
    @ResponseBody
    public ReturnInfo<List<MaiteUser>> GetMainContentData() {
        List<MaiteMainContentEntity> list = new ArrayList<>();
        // 获取用户信息
        List<MaiteUser> maiteUserList = maiteUserService.getList();
        for (MaiteUser item : maiteUserList) {
            MaiteMainContentEntity entity = new MaiteMainContentEntity();
            entity.setUin(item.getUin());
            entity.setUserName(item.getUserName());
            entity.setIsArrive(item.getIsArrive());
            entity.setIsComment(item.getIsComment());
            //TODO orderid day
            entity.setAddTime(item.getShuadanTime().toString());
        }
        ReturnInfo<List<MaiteUser>> returnInfo = new ReturnInfo<>();
        returnInfo.set(true, 0, "查询列表成功", maiteUserList);
        return returnInfo;
    }
}
