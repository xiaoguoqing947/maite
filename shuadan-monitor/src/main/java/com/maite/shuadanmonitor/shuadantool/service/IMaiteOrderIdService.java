package com.maite.shuadanmonitor.shuadantool.service;

import com.maite.shuadanmonitor.shuadantool.entity.MaiteOrderId;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xgq
 * @since 2021-01-21
 */
public interface IMaiteOrderIdService extends IService<MaiteOrderId> {

    public MaiteOrderId GetOrderId(int uin);
}
