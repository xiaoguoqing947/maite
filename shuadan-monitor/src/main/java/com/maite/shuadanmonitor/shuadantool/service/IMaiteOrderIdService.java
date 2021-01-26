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

    /**
     * 根据UIn获取用户订单编号
     * @param uin
     * @return
     */
    public MaiteOrderId GetOrderId(int uin);

    /**
     * 根据订单ID查询用户选择的商品信息
     * @param orderId
     * @return
     */
    public String queryGoods(String orderId);
}
