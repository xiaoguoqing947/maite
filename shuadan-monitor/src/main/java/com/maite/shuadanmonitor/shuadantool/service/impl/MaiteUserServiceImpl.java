package com.maite.shuadanmonitor.shuadantool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maite.shuadanmonitor.shuadantool.entity.MaiteUser;
import com.maite.shuadanmonitor.shuadantool.mapper.MaiteUserMapper;
import com.maite.shuadanmonitor.shuadantool.service.IMaiteUserService;
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
 * @since 2021-01-20
 */
@Service
public class MaiteUserServiceImpl extends ServiceImpl<MaiteUserMapper, MaiteUser> implements IMaiteUserService {
    private static final Logger log = LoggerFactory.getLogger(MaiteUserServiceImpl.class);

    @Resource
    private MaiteUserMapper maiteUserMapper;

    @Override
    public List<MaiteUser> getList() {
        List<MaiteUser> maiteUsers = null;
        try {
            QueryWrapper<MaiteUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.ne("IsComment", 1);
            maiteUsers = maiteUserMapper.selectList(queryWrapper);
        } catch (Exception ex) {
            log.error("查询数据列表异常", ex);
        }
        return maiteUsers;
    }

    @Override
    public List<MaiteUser> getPageList(int page, int size) {
        List<MaiteUser> maiteUsers = null;
        try {
            QueryWrapper<MaiteUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.ne("IsComment", 1);
            queryWrapper.last(MessageFormat.format("LIMIT {0},{1}", (page - 1) * size, size));
            maiteUsers = maiteUserMapper.selectList(queryWrapper);
        } catch (Exception ex) {
            log.error("查询分页数据列表异常", ex);
        }
        return maiteUsers;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = maiteUserMapper.selectCount(new QueryWrapper<>());
        } catch (Exception ex) {
            log.error("查询数据列表总数异常", ex);
        }
        return count;
    }
}
