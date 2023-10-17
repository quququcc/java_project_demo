package com.fs.dylan.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author dylan
 * @since 2023-08-14
 */
@Getter
@Setter
@TableName("project_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主健id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String describe;

    /**
     * 角色类型 1表示客服管理权限
     */
    private Integer type;

    /**
     * 权重
     */
    private Integer sort;

    /**
     * 是否被删除 1 表示删除 0 表示未删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;


}
