package com.fs.dylan.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author dylan
 * @title: UserLoginDTO
 * @projectName dylan
 * @description:
 * @date 2023/8/16
 */
@Data
public class UserLoginDTO {
    @ApiModelProperty(value = "用户邮箱")
    @NotNull(message = "用户邮箱不能为空")
    private String email;
}
