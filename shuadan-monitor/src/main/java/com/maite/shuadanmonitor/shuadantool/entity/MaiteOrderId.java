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
 * @since 2021-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MaiteOrderId implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 用户Uin
     */
    @TableField("Uin")
    private Integer Uin;

    /**
     * 订单编号
     */
    @TableField("OrderId")
    private String OrderId;

    /**
     * 商品ID
     */
    @TableField("GoodId")
    private String GoodId;

    /**
     * 添加时间
     */
    @TableField("AddTime")
    private LocalDateTime AddTime;


}
