package com.maite.shuadanmonitor.shuadantool.entity;

import lombok.Data;

/**
 * @author ：xiao guo qing
 * @date ：2021/1/21 11:31
 * @description：
 */
@Data
public class MaiteMainContentEntity {

    private Integer uin;

    private  Integer day;

    private String userName;

    private String orderId;

    private String goodId;

    private String addTime;

    private Integer isArrive;

    private Integer isComment;
}
