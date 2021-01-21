package com.maite.shuadanmonitor.shuadantool.service;

import com.maite.shuadanmonitor.shuadantool.entity.MaiteUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xgq
 * @since 2021-01-20
 */
public interface IMaiteUserService extends IService<MaiteUser> {

    //#region 获取用户列表 [getList()]

    /**
     * 获取用户列表
     * @return 返回用户列表
     */
    public List<MaiteUser> getList();
    //#endregion
}
