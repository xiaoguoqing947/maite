package com.maite.shuadanmonitor.shuadantool.service;

import com.maite.shuadanmonitor.shuadantool.entity.MaiteDictionary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteUser;

import java.util.List;

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
    public MaiteDictionary queryKeyName(String type,String value);

    //#region 获取字典列表数量 [getCount()]

    /**
     * 获取字典列表数量
     * @return 返回分页字典列表数量
     */
    public int getCount();
    //#endregion

    //#region 获取分页字典列表 [getPageList(int page,int size)]

    /**
     * 获取分页字典列表
     * @return 返回分页字典列表
     */
    public List<MaiteDictionary> getPageList(int page, int size);
    //#endregion

    //#region 获取指定类型的字典唯一value值 [queryDcisValue(String typeName)]

    /**
     * 获取指定类型的字典唯一value值
     * @return vlaue
     */
    public int queryDcisValue(String typeName);
    //#endregion
}
