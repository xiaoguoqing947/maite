package com.maite.shuadanmonitor.shuadantool.service;

import com.maite.shuadanmonitor.shuadantool.entity.MaiteDictionary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xgq
 * @since 2021-01-23
 */
public interface IMaiteDictionaryService extends IService<MaiteDictionary> {

    /**
     * 根据类型和值获取字典名
     * @param type
     * @param value
     * @return
     */
    public String queryKeyName(String type,String value);
}
