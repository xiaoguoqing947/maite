package com.maite.shuadanmonitor.shuadantool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MaiteGoodComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 商品SKU名称
     */
    @TableField("Model")
    private String Model;

    /**
     * 评论信息
     */
    @TableField("Comment")
    private String Comment;

    /**
     * 商品类别名称
     */
    @TableField("TypeName")
    private Integer TypeName;


}
