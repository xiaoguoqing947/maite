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
    public List<MaiteUser> getPageList(int page,int size,String isComment);
    //#endregion

    //#region 获取用户列表数量 [getCount()]

    /**
     * 获取用户列表数量
     * @return 返回分页用户列表数量
     */
    public int getCount(String isComment);
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

    //#region 根据用户名更新用户刷单时间 [updateByUserName(MaiteUser maiteUser)]

    /**
     * 根据用户名更新用户刷单时间
     */
    public void updateByUserName(MaiteUser maiteUser);
    //#endregion

    //#region 查询待评论的用户列表 [queryCommentUserList()]

    /**
     * 查询待评论的用户列表
     */
    public List<String> queryCommentUserList();
    //#endregion


    //#region 查询可再次购买的用户列表 [queryBugUserList()]

    /**
     * 查询可再次购买的用户列表
     */
    public List<String> queryBugUserList();
    //#endregion

    //#region 根据用户名查询相似用户名列表 [queryUserNameList()]

    /**
     * 根据用户名查询相似用户名列表
     */
    public List<String> queryUserNameList();
    //#endregion

    //#region 获取用户列表 [getUserList()]

    /**
     * 获取用户列表
     */
    public List<MaiteUser> getUserList();
    //#endregion
}
