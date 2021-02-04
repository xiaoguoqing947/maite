package com.maite.shuadanmonitor.shuadantool.controller;


import cn.hutool.core.date.DateUtil;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteUser;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteOrderIdService;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
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
    @Autowired
    IMaiteOrderIdService maiteOrderIdService;


    @ResponseBody
    @GetMapping("/queryUserNameList")
    public List<String> queryUserNameList() {
        List<String> userNameList = null;
        userNameList = maiteUserService.queryUserNameList();
        return userNameList;
    }

    @ResponseBody
    @GetMapping("/queryList")
    public List<MaiteUser> queryList() {
        List<MaiteUser> list = maiteUserService.getUserList();
        return list;
    }

    @ResponseBody
    @GetMapping("/delete/{uin}")
    public Boolean delete(@PathVariable int uin) {
        Boolean result = false;
        HashMap<String, Object> queryMap = new HashMap<>();
        queryMap.put("Uin", uin);
        try {
            if (maiteOrderIdService.removeByMap(queryMap) && maiteUserService.removeById(uin)) {
                result = true;
            }
        } catch (Exception e) {
            log.error("[delete]删除指定用的信息", e);
        }
        return result;
    }

    @ResponseBody
    @PostMapping("/queryTableList")
    public HashMap<String, Object> queryList(@RequestParam("page") int page, @RequestParam("size") int size) {
        HashMap<String, Object> resultMap = new HashMap<>();
        int totalCount = maiteUserService.getCount("");
        List<MaiteUser> list = maiteUserService.getPageList(page, size,"");
        resultMap.put("code", 0);
        resultMap.put("msg", "查询列表成功");
        resultMap.put("data", list);
        resultMap.put("count", totalCount);
        return resultMap;
    }
}
