package com.fs.dylan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 雇员权限中间表
 * </p>
 *
 * @author dylan
 * @since 2023-08-14
 */
@Getter
@Setter
@TableName("project_employee_has_permission")
public class EmployeeHasPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主健id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 雇员id
     */
    private Integer employeeId;

    /**
     * 权限id
     */
    private Integer permissionId;


}
