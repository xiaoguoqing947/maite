package com.maite.shuadanmonitor.shuadantool.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：xiao guo qing
 * @date ：2021/1/21 11:31
 * @description：
 */
@Data
@NoArgsConstructor
public class MaiteAdmin {
    private  Integer day;
    private String userName;
    private String orderId;
    private String goodId;
    private Boolean isArrive;
    private Boolean isComment;
}
