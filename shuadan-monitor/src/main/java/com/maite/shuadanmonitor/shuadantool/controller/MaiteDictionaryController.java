package com.maite.shuadanmonitor.shuadantool.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 *  前端控制器
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
    public List<MaiteDictionary> GetDicTypeData(){
        List<MaiteDictionary> list = null;
        try{
            QueryWrapper<MaiteDictionary> queryWrapper = new QueryWrapper<>();
            list =  maiteDictionaryService.list(queryWrapper);
        }catch (Exception e){
            log.error("查询字典列表异常",e);
        }
        return list;
    }
}
