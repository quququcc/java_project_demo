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
 * 权限表
 * </p>
 *
 * @author dylan
 * @since 2023-08-14
 */
@Getter
@Setter
@TableName("project_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主健id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父类id
     */
    private Integer parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 组件路由
     */
    private String component;

    /**
     * 路由图标
     */
    private String icon;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 路由标题
     */
    private String title;

    /**
     * 是否需要登录
     */
    @TableField("is_need_login")
    private Integer needLogin;

    /**
     * 是否是菜单
     */
    @TableField("is_menu")
    private Integer menu;

    /**
     * 排序字段
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
