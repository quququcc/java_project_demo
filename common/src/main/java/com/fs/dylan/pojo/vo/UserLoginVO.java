package com.fs.dylan.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author dylan
 * @title: UserVO
 * @projectName dylan
 * @description:
 * @date 2023/8/16
 */
@Data
public class UserLoginVO {
    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户职位")
    private String job;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户token")
    private String token;
}
