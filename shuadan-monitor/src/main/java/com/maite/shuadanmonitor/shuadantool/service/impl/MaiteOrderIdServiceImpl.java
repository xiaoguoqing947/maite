package com.maite.shuadanmonitor.shuadantool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteOrderId;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteUser;
import com.maite.shuadanmonitor.shuadantool.mapper.MaiteOrderIdMapper;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteOrderIdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xgq
 * @since 2021-01-21
 */
@Service
public class MaiteOrderIdServiceImpl extends ServiceImpl<MaiteOrderIdMapper, MaiteOrderId> implements IMaiteOrderIdService {
    private static final Logger log = LoggerFactory.getLogger(MaiteOrderIdServiceImpl.class);

    @Resource
    private MaiteOrderIdMapper maiteOrderIdMapper;


    @Override
    public MaiteOrderId GetOrderId(int uin) {
        MaiteOrderId orderIdEntity = null;
        try {
            QueryWrapper<MaiteOrderId> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("Uin", uin).orderByDesc("AddTime");
            queryWrapper.last("LIMIT 1");
            orderIdEntity = maiteOrderIdMapper.selectOne(queryWrapper);
        } catch (Exception ex) {
            log.error("[GetOrderId]查询订单编号异常", ex);
        }
        return orderIdEntity;
    }

    @Override
    public String queryGoods(String orderId) {
        String goods = "";
        try {
            QueryWrapper<MaiteOrderId> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("OrderId", orderId).orderByDesc("AddTime");
            queryWrapper.last("LIMIT 1");
            goods = maiteOrderIdMapper.selectOne(queryWrapper).getGoodId();
        } catch (Exception ex) {
            log.error("[queryGoods]查询商品信息异常", ex);
        }
        return goods;
    }

    @Override
    public Date queryRecentDate(int uin) {
        Date recentDate = null;
        try {
            QueryWrapper<MaiteOrderId> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("Uin", uin).orderByDesc("AddTime");
            queryWrapper.last("LIMIT 1");
            recentDate = maiteOrderIdMapper.selectOne(queryWrapper).getAddTime();
        } catch (Exception ex) {
            log.error("[queryRecentDate]根据uin获取用户添加订单的最新时间异常", ex);
        }
        return recentDate;
    }
}
