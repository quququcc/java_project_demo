package com.fs.dylan.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author dylan
 * @title: UserDTO
 * @projectName dylan
 * @description:
 * @date 2023/8/16
 */
@Getter
@Setter
@ToString
public class UserRegisterDTO {
    @NotNull(message = "openId不能为空")
    @ApiModelProperty(value = "openId")
    private String openId;

    @NotNull(message = "用户邮箱不能为空")
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @NotNull(message = "用户密码不能为空")
    @ApiModelProperty(value = "用户密码")
    private String name;

    @NotNull(message = "用户昵称不能为空")
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @NotNull(message = "用户职位不能为空")
    @ApiModelProperty(value = "用户职位")
    private String job;

    @NotNull(message = "用户头像不能为空")
    @ApiModelProperty(value = "用户头像")
    private String avatar;
}
