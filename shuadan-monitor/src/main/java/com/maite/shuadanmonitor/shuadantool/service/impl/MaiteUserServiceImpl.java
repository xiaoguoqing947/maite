package com.maite.shuadanmonitor.shuadantool.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
            log.error("[getList]查询数据列表异常", ex);
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
            log.error("[getPageList]查询分页数据列表异常", ex);
        }
        return maiteUsers;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = maiteUserMapper.selectCount(new QueryWrapper<>());
        } catch (Exception ex) {
            log.error("[getCount]查询数据列表总数异常", ex);
        }
        return count;
    }

    @Override
    public int getUinByUserName(String userName) {
        QueryWrapper<MaiteUser> queryWrapper = null;
        try {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("UserName", userName).select("Uin");
        }catch (Exception e){
            log.error("[getUinByUserName]查询数据UIN异常", e);
        }
        return maiteUserMapper.selectOne(queryWrapper).getUin();
    }

    @Override
    public Boolean judgeUserIsExist(String userName) {
        QueryWrapper<MaiteUser> queryWrapper = null;
        try {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("UserName", userName);
        }catch (Exception e){
            log.error("[judgeUserIsExist]查询指定用户名是否存在异常", e);
        }
        return maiteUserMapper.selectOne(queryWrapper) != null;
    }

    @Override
    public void updateTimeByUserName(MaiteUser maiteUser) {
        UpdateWrapper<MaiteUser> updateWrapper = null;
        try {
            updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("UserName", maiteUser.getUserName());
            maiteUserMapper.update(maiteUser,updateWrapper);
        }catch (Exception e){
            log.error("[updateTimeByUserName]更新刷单时间异常",e);
        }
    }

    @Override
    public List<String> queryCommentUserList() {
        List<String> userNameList = new ArrayList<>();
        try {
            QueryWrapper<MaiteUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("IsArrive", 1).eq("IsComment", 0).select("UserName");;
            Iterator<MaiteUser> iterator = maiteUserMapper.selectList(queryWrapper).iterator();
            while(iterator.hasNext()) {
                //iterator.next()返回迭代的下一个元素
                userNameList.add(iterator.next().toString());
            }
        }catch (Exception e){
            log.error("[queryCommentUserList]查询待评论的用户列表存在异常", e);
        }
        return userNameList;
    }

    @Override
    public List<String> queryBugUserList() {
        List<String> userNameList = new ArrayList<>();
        try {
            QueryWrapper<MaiteUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("IsArrive", 1).eq("IsComment", 1);
            List<MaiteUser> maiteUsers = maiteUserMapper.selectList(queryWrapper);
            Date today = new Date();
            for (MaiteUser entity: maiteUsers) {
                if((int) DateUtil.betweenDay(today, entity.getShuadanTime(), true) > 30){
                    userNameList.add(entity.getUserName());
                }
            }
        }catch (Exception e){
            log.error("[queryBugUserList]查询可再次购买的用户列表", e);
        }
        return userNameList;
    }
}
