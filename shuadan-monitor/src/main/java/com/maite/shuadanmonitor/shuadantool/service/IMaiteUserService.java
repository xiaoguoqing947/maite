package com.maite.shuadanmonitor.shuadantool.service;

import com.maite.shuadanmonitor.shuadantool.entity.MaiteUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
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

    //#region 获取分页用户列表 [getPageList(int page,int size)]

    /**
     * 获取分页用户列表
     * @return 返回分页用户列表
     */
    public List<MaiteUser> getPageList(int page,int size);
    //#endregion

    //#region 获取用户列表数量 [getCount()]

    /**
     * 获取用户列表数量
     * @return 返回分页用户列表数量
     */
    public int getCount();
    //#endregion

    //#region 根据用户名获取用户Uin [getUinByUserName(String userName)]

    /**
     * 根据用户名获取用户Uin
     * @return 返回UIN
     */
    public int getUinByUserName(String userName);
    //#endregion

    //#region 根据用户名判断用户是否存在 [judgeUserIsExist(String userName)]

    /**
     * 根据用户名获取用户Uin
     * @return 返回UIN
     */
    public Boolean judgeUserIsExist(String userName);
    //#endregion

    //#region 根据用户名更新用户刷单时间 [updateTimeByUserName(MaiteUser maiteUser)]

    /**
     * 根据用户名更新用户刷单时间
     */
    public void updateTimeByUserName(MaiteUser maiteUser);
    //#endregion

}
