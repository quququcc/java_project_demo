package com.fs.dylan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 雇员角色中间表
 * </p>
 *
 * @author dylan
 * @since 2023-08-14
 */
@Getter
@Setter
@TableName("project_employee_has_role")
public class EmployeeHasRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 雇员id
     */
    private Integer employeeId;

    /**
     * 角色id
     */
    private Integer roleId;


}
