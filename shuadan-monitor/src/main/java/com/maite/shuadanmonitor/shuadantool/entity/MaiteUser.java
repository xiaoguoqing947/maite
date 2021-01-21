package com.maite.shuadanmonitor.shuadantool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xgq
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MaiteUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一值
     */
    @TableId(value = "Uin", type = IdType.AUTO)
    private Integer Uin;

    /**
     * 用户名
     */
    @TableField("UserName")
    private String UserName;

    /**
     * 联系电话
     */
    @TableField("Phone")
    private String Phone;

    /**
     * 与用户关系
     */
    @TableField("Relation")
    private Integer Relation;

    /**
     * 物流是否到达
     */
    @TableField("IsArrive")
    private Integer IsArrive;

    /**
     * 订单是否评论
     */
    @TableField("IsComment")
    private Integer IsComment;

    /**
     * 刷单时间
     */
    @TableField("ShuadanTime")
    private LocalDateTime ShuadanTime;


}
