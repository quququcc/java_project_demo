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
 * 雇员表
 * </p>
 *
 * @author dylan
 * @since 2023-08-14
 */
@Getter
@Setter
@TableName("project_employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主健id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 飞书 open_id
     */
    private String openId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 职位
     */
    private String job;

    /**
     * 头像
     */
    private String avatar;

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
