package com.maite.shuadanmonitor.shuadantool.controller;

import cn.hutool.core.date.DateUtil;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteMainContentEntity;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteOrderId;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    @PostMapping("/api/admin")
    @ResponseBody
    public HashMap<String,Object> GetMainContentData(@RequestParam("page")int page,@RequestParam("size")int size) {
        List<MaiteMainContentEntity> list = new ArrayList<>();
        // 获取用户信息
        int totalCount = maiteUserService.getCount();
        List<MaiteUser> maiteUserList = maiteUserService.getPageList(page,size);
        for (MaiteUser item : maiteUserList) {
            MaiteMainContentEntity entity = new MaiteMainContentEntity();
            entity.setUin(item.getUin());
            entity.setUserName(item.getUserName());
            entity.setIsArrive(item.getIsArrive());
            entity.setIsComment(item.getIsComment());
            entity.setAddTime(DateUtil.formatDate(item.getShuadanTime()));
            entity.setDay((int) DateUtil.betweenDay(new Date(),item.getShuadanTime(),true));
            entity.setOrderId(maiteOrderIdService.GetOrderId(item.getUin()));
            list.add(entity);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("msg","查询列表成功");
        resultMap.put("data",list);
        resultMap.put("count",totalCount);
        return resultMap;
    }
}
