package com.maite.shuadanmonitor.shuadantool.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteDictionary;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteUser;
import com.maite.shuadanmonitor.shuadantool.file.FileUtils;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteDictionaryService;
import com.maite.shuadanmonitor.utils.ReturnInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
            log.error("[GetDicTypeData]查询字典列表异常", e);
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
    public ReturnInfo<Boolean> update(@RequestBody MaiteDictionary maiteDictionary) {
        ReturnInfo<Boolean> returnInfo = new ReturnInfo<>();
        UpdateWrapper<MaiteDictionary> updateWrapper = null;
        try {
            if (maiteDictionary.getKeyName().isEmpty()) {
                returnInfo.set(false, 1, "字典【关键值】不能为空！");
            } else if (maiteDictionaryService.queryKeyNameIsExist(maiteDictionary.getKeyName())) {
                returnInfo.set(false, 2, "字典【关键值】命名重复，请重新命名！");
            } else {
                updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("Id", maiteDictionary.getId());
                Boolean result = maiteDictionaryService.update(maiteDictionary, updateWrapper);
                returnInfo.set(true, 0, result ? "更新成功" : "更新失败");
            }
        } catch (Exception ex) {
            log.error("[update]更新字典数据异常", ex);
        }
        return returnInfo;
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

    @GetMapping("/{module}")
    @ResponseBody
    public Boolean addDictionaryModule(@PathVariable String module) {
        boolean result = false;
        String typeName = "";
        String typeDesc = "";
        MaiteDictionary maiteDictionary = new MaiteDictionary();
        maiteDictionary.setKeyDesc("【待填写】");
        if ("addGoodModule".equals(module)) {
            typeName = "good";
            typeDesc = "商品";
        } else if ("addGoodTypeModule".equals(module)) {
            maiteDictionary.setType("goodType");
            typeName = "goodType";
            typeDesc = "商品类别名称";
        } else if ("addRelationModule".equals(module)) {
            maiteDictionary.setType("relation");
            typeName = "relation";
            typeDesc = "关系";
        }
        maiteDictionary.setType(typeName);
        int keyValue = maiteDictionaryService.queryDcisValue(typeName);
        maiteDictionary.setDcisValue(keyValue);
        maiteDictionary.setTypeDesc(typeDesc);
        result = maiteDictionaryService.save(maiteDictionary);
        return result;
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public HashMap<String, Object> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("goodName") String goodName) {
        HashMap<String, Object> resultMap = new HashMap<>();
        if (file.isEmpty()) {
            resultMap.put("msg", "上传失败，请选择文件");
            resultMap.put("code", 1);
        } else if (goodName.isEmpty()) {
            resultMap.put("msg", "请输入商品名称");
            resultMap.put("code", 1);
        }else if(!maiteDictionaryService.queryKeyNameIsExist(goodName)){
            resultMap.put("msg", "请输入指定商品");
            resultMap.put("code", 1);
        } else{
            String fileName = file.getOriginalFilename();
            ApplicationHome h = new ApplicationHome(getClass());
            File jarF = h.getSource();
            //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
            String dirPath = jarF.getParentFile().toString()+"/images/goods/";
            File filePath=new File(dirPath);
            if(!filePath.exists()){
                filePath.mkdirs();
            }
            try {
                FileUtils.fileupload(file.getBytes(), dirPath, fileName);
                UpdateWrapper<MaiteDictionary> updateWrapper = new UpdateWrapper<>();
                MaiteDictionary maiteDictionary = new MaiteDictionary();
                maiteDictionary.setRemark("/images/goods/" + fileName);
                updateWrapper.eq("Type", "good");
                updateWrapper.eq("KeyName", goodName);
                maiteDictionaryService.update(maiteDictionary, updateWrapper);
                resultMap.put("msg", "上传成功");
                resultMap.put("code", 0);
            } catch (IOException e) {
                log.error("[uploadFile]上传文件异常", e);
            } catch (Exception ex) {
                log.error("[uploadFile]更新商品Remark信息异常", ex);
            }
        }
        return resultMap;
    }
}
