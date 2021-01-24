package com.maite.shuadanmonitor.shuadantool.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVoEntity {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 联系方式
     */
    private String phoneNumber;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 下单日期
     */
    private Date orderDate;
    /**
     * 商品编号
     */
    private String good;
    /**
     * 对象关系
     */
    private int relation;
    /**
     * 物流是否到达
     */
    private int wuliuSwitch;
    /**
     * 该用户是否评论
     */
    private int commentSwitch;
}
