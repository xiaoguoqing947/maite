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
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MaiteDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 数字字典类型
     */
    @TableField("Type")
    private String Type;

    /**
     * 数字字典类型描述
     */
    @TableField("TypeDesc")
    private String TypeDesc;

    /**
     * 字典值
     */
    @TableField("DcisValue")
    private Integer DcisValue;

    /**
     * 字典key
     */
    @TableField("Key")
    private String Key;

    /**
     * 字典key描述
     */
    @TableField("KeyDesc")
    private String KeyDesc;

    /**
     * 字典标记
     */
    @TableField("Remark")
    private String Remark;


}
