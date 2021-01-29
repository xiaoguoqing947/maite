package com.maite.shuadanmonitor.shuadantool.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteMainContentEntity;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteOrderId;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteUser;
import com.maite.shuadanmonitor.shuadantool.entity.vo.UserVoEntity;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteDictionaryService;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteOrderIdService;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteUserService;
import com.maite.shuadanmonitor.shuadantool.service.impl.MaiteOrderIdServiceImpl;
import com.maite.shuadanmonitor.shuadantool.service.impl.MaiteUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
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

    //#region 主界面数据加载


    @PostMapping("/api/admin")
    @ResponseBody
    public HashMap<String, Object> GetMainContentData(@RequestParam("page") int page, @RequestParam("size") int size) {
        List<MaiteMainContentEntity> list = new ArrayList<>();
        // 获取用户信息
        int totalCount = maiteUserService.getCount();
        List<MaiteUser> maiteUserList = maiteUserService.getPageList(page, size);
        MaiteOrderId orderIdEntity = null;
        for (MaiteUser item : maiteUserList) {
            orderIdEntity = maiteOrderIdService.GetOrderId(item.getUin());
            MaiteMainContentEntity entity = new MaiteMainContentEntity();
            entity.setUin(item.getUin());
            entity.setUserName(item.getUserName());
            entity.setIsArrive(item.getIsArrive());
            entity.setIsComment(item.getIsComment());
            entity.setAddTime(DateUtil.formatDate(orderIdEntity.getAddTime()));
            entity.setDay((int) DateUtil.betweenDay(new Date(), orderIdEntity.getAddTime(), true));
            entity.setOrderId(orderIdEntity.getOrderId());
            list.add(entity);
        }
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", 0);
        resultMap.put("msg", "查询列表成功");
        resultMap.put("data", list);
        resultMap.put("count", totalCount);
        return resultMap;
    }

    @GetMapping("api/commentAndBudUserList")
    @ResponseBody
    public HashMap<String, Object> queryCommentAndBugUserList() {
        HashMap<String, Object> resultMap = new HashMap<>();
        List<String> commentUserList = maiteUserService.queryCommentUserList();
        List<String> bugUserList = maiteUserService.queryBugUserList();
        resultMap.put("commentUserList", commentUserList);
        resultMap.put("bugUserList", bugUserList);
        return resultMap;
    }
    //#endregion

    //#region 用户信息录入模块 [AddUserMessage(@RequestBody UserVoEntity userVoEntity) ]
    @Transactional
    @PostMapping("/api/addUserMessage")
    @ResponseBody
    public Boolean AddUserMessage(@RequestBody UserVoEntity userVoEntity) {
        boolean result = true;
        try {
            //#region 用户相关信息对象赋值 TODO 设置回滚对象，防止插入失败
            String userName = userVoEntity.getUserName();
            MaiteUser maiteUser = new MaiteUser();
            maiteUser.setUserName(userName);
            maiteUser.setIsArrive(userVoEntity.getWuliuSwitch());
            maiteUser.setIsComment(userVoEntity.getCommentSwitch());
            maiteUser.setRelation(userVoEntity.getRelation());
            maiteUser.setPhone(userVoEntity.getPhoneNumber());
            maiteUser.setShuadanTime(userVoEntity.getOrderDate());
            if (!maiteUserService.judgeUserIsExist(userName)) {
                //添加用户信息到maite_user表
                maiteUserService.save(maiteUser);
            } else {
                //更新用户下单时间
                maiteUserService.updateTimeByUserName(maiteUser);
            }
            int uin = maiteUserService.getUinByUserName(userName);
            updateUser(maiteUser, userVoEntity.getOrderDate(), uin);
            MaiteOrderId orderIdEntity = new MaiteOrderId();
            orderIdEntity.setAddTime(userVoEntity.getOrderDate());
            orderIdEntity.setGoodId(userVoEntity.getGood());
            orderIdEntity.setOrderId(userVoEntity.getOrderId());
            orderIdEntity.setUin(uin);
            maiteOrderIdService.save(orderIdEntity);
            //#endregion
        } catch (Exception e) {
            result = false;
            log.error(MessageFormat.format("[AddUserMessage]插入用户信息失败[UserVoEntity]{0}",userVoEntity.toString()), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }


    /**
     * 更新用户表时间参数，更新的时间一定为用户最后下单的时间
     *
     * @param maiteUser
     * @param addDate
     */
    private void updateUser(MaiteUser maiteUser, Date addDate, int uin) {
        Date recentDate = maiteOrderIdService.queryRecentDate(uin);
        if (DateUtil.compare(recentDate, addDate) < 0) {
            maiteUserService.updateTimeByUserName(maiteUser);
        }
    }
    //#endregion

    @GetMapping("/api/updateState/{type}/{data}")
    @ResponseBody
    public Boolean lockUser(@PathVariable String type, @PathVariable String data) {
        //data: 值_用户名UIN
        String[] arr = data.split("_");
        UpdateWrapper<MaiteUser> updateWrapper = new UpdateWrapper<>();
        MaiteUser maiteUser = new MaiteUser();
        if (type.equals("IsArrive")) {
            maiteUser.setIsArrive(Integer.valueOf(arr[0]));
        } else if (type.equals("IsComment")) {
            maiteUser.setIsComment(Integer.valueOf(arr[0]));
        }
        updateWrapper.set(type, arr[0]);
        updateWrapper.eq("Uin", arr[1]);
        return maiteUserService.update(maiteUser, updateWrapper);
    }

    @GetMapping("/api/queryGoods/{orderId}")
    @ResponseBody
    public HashMap<String, Object> queryUserGoodsMessageByOrderId(@PathVariable String orderId) {
        String goods = maiteOrderIdService.queryGoods(orderId);
        String[] goodArr = goods.split(",");
        HashMap<String, Object> resultMap = new HashMap<>();
        List<String> goodNameList = new ArrayList<>();
        for (int i = 0; i < goodArr.length; i++) {
            goodNameList.add(maiteDictionaryService.queryKeyName("good", goodArr[i]));
        }
        resultMap.put("data", goodNameList);
        return resultMap;
    }
}
