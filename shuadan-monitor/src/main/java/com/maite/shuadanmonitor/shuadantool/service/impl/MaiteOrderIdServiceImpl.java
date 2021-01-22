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
    public String GetOrderId(int uin) {
        String orderId = "";
        try {
            QueryWrapper<MaiteOrderId> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("Uin", uin).orderByDesc("AddTime").select("OrderId");
            MaiteOrderId orderIdEntity = maiteOrderIdMapper.selectOne(queryWrapper);
            if (orderIdEntity != null) {
                orderId = orderIdEntity.getOrderId();
            }
        } catch (Exception ex) {
            log.error("查询订单编号异常", ex);
        }
        return orderId;
    }
}
