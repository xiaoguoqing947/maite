package com.maite.shuadanmonitor.shuadantool.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteDictionary;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteDictionaryService;
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
@RequestMapping("/api/dictionary")
public class MaiteDictionaryController {
    private static final Logger log = LoggerFactory.getLogger(MaiteDictionaryController.class);

    @Autowired
    IMaiteDictionaryService maiteDictionaryService;

    @GetMapping("/GetDicTypeData")
    @ResponseBody
    public List<MaiteDictionary> GetDicTypeData() {
        List<MaiteDictionary> list = null;
        try {
            QueryWrapper<MaiteDictionary> queryWrapper = new QueryWrapper<>();
            list = maiteDictionaryService.list(queryWrapper);
        } catch (Exception e) {
            log.error("查询字典列表异常", e);
        }
        return list;
    }

    @GetMapping("/queryList")
    @ResponseBody
    public HashMap<String, Object> queryList(@RequestParam("page") int page, @RequestParam("size") int size) {
        List<MaiteDictionary> list = null;
        int totalCount = 0;
        list = maiteDictionaryService.getPageList(page, size);
        totalCount = maiteDictionaryService.getCount();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", 0);
        resultMap.put("msg", "查询列表成功");
        resultMap.put("data", list);
        resultMap.put("count", totalCount);
        return resultMap;
    }

    @PostMapping("/update")
    @ResponseBody
    public Boolean update(@RequestBody MaiteDictionary maiteDictionary) {
        Boolean result = false;
        UpdateWrapper<MaiteDictionary> updateWrapper = null;
        try {
            updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("Id", maiteDictionary.getId());
            result = maiteDictionaryService.update(maiteDictionary, updateWrapper);
        } catch (Exception ex) {
            log.error("[update]更新字典数据异常", ex);
        }
        return result;
    }

    @PostMapping("/delete")
    @ResponseBody
    public Boolean delete(@RequestParam("id") int id) {
        Boolean result = false;
        try {
            result = maiteDictionaryService.removeById(id);
        } catch (Exception ex) {
            log.error("[delete]删除字典数据异常", ex);
        }
        return result;
    }
}
