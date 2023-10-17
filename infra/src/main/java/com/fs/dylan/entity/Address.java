package com.fs.dylan.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author dylan
 * @since 2023-09-03
 */
@Getter
@Setter
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID，雪花算法生成
     */
    @TableField(value = "id")
    @ExcelProperty({"序号"})
    private Long id;

    /**
     * 省市县的json字符串
     */
    @TableField(value = "region")
    @ExcelProperty({"省市县"})
    private String region;

    /**
     * 街道地址
     */
    @ExcelProperty({"街道"})
    @TableField(value = "street")
    private String street;

    /**
     * 名字
     */
    @ExcelProperty({"姓名"})
    @TableField(value = "name")
    private String name;

    /**
     * 手机号码
     */
    @ExcelProperty({"电话"})
    @TableField(value = "phone")
    private String phone;

    /**
     * 是否是默认地址 1 为默认地址，默认为 0
     */
    @ExcelProperty({"默认地址"})
    @TableField(value = "`default`")
    private Integer defaultAddress;

    /**
     * 创建时间
     */
    @ExcelProperty({"创建时间"})
    @TableField(fill = FieldFill.INSERT, value = "created_at")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @ExcelProperty({"更新时间"})
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * 雇佣id
     */
    @ExcelProperty({"雇佣id"})
    @TableField(value = "employee_id")
    private Integer employeeId;


}
