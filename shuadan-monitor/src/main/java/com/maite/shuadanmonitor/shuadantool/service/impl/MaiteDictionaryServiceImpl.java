package com.maite.shuadanmonitor.shuadantool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteDictionary;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteOrderId;
import com.maite.shuadanmonitor.shuadantool.mapper.MaiteDictionaryMapper;
import com.maite.shuadanmonitor.shuadantool.mapper.MaiteOrderIdMapper;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xgq
 * @since 2021-01-23
 */
@Service
public class MaiteDictionaryServiceImpl extends ServiceImpl<MaiteDictionaryMapper, MaiteDictionary> implements IMaiteDictionaryService {
    private static final Logger log = LoggerFactory.getLogger(MaiteDictionaryServiceImpl.class);

    @Resource
    private MaiteDictionaryMapper maiteDictionaryMapper;

    @Override
    public String queryKeyName(String type, String value) {
        String keyName = "";
        try {
            QueryWrapper<MaiteDictionary> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("Type", type).eq("DcisValue",value);
            queryWrapper.last("LIMIT 1");
            keyName = maiteDictionaryMapper.selectOne(queryWrapper).getKeyName();
        } catch (Exception ex) {
            log.error("[queryKeyName]查询商品信息异常", ex);
        }
        return keyName;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = maiteDictionaryMapper.selectCount(new QueryWrapper<>());
        } catch (Exception ex) {
            log.error("[getCount]查询数据列表总数异常", ex);
        }
        return count;
    }
}
