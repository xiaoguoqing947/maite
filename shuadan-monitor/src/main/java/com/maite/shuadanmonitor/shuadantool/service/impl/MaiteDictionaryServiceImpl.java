package com.maite.shuadanmonitor.shuadantool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteDictionary;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteOrderId;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteUser;
import com.maite.shuadanmonitor.shuadantool.mapper.MaiteDictionaryMapper;
import com.maite.shuadanmonitor.shuadantool.mapper.MaiteOrderIdMapper;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;

/**
 * <p>
 * 服务实现类
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
    public MaiteDictionary queryKeyName(String type, String value) {
        MaiteDictionary dictionary = null;
        try {
            QueryWrapper<MaiteDictionary> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("Type", type).eq("DcisValue", value);
            queryWrapper.last("LIMIT 1");
            dictionary = maiteDictionaryMapper.selectOne(queryWrapper);
        } catch (Exception ex) {
            log.error("[queryKeyName]查询商品信息异常", ex);
        }
        return dictionary;
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

    @Override
    public List<MaiteDictionary> getPageList(int page, int size) {
        List<MaiteDictionary> maiteDictionaries = null;
        try {
            QueryWrapper<MaiteDictionary> queryWrapper = new QueryWrapper<>();
            queryWrapper.last(MessageFormat.format("LIMIT {0},{1}", (page - 1) * size, size));
            maiteDictionaries = maiteDictionaryMapper.selectList(queryWrapper);
        } catch (Exception ex) {
            log.error("[getPageList]查询分页数据列表异常", ex);
        }
        return maiteDictionaries;
    }

    @Override
    public int queryDcisValue(String typeName) {
        int result = 1;
        try {
            QueryWrapper<MaiteDictionary> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("Type", typeName).orderByDesc("DcisValue");
            result = maiteDictionaryMapper.selectOne(queryWrapper).getDcisValue() + 1;
        } catch (Exception ex) {
            log.error("[queryDcisValue]获取指定类型的字典唯一value值", ex);
        }
        return result;
    }
}
