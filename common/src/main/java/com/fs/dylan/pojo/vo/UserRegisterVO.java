package com.fs.dylan.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author dylan
 * @title: UserRegisterVO
 * @projectName dylan
 * @description:
 * @date 2023/8/16
 */
@Data
public class UserRegisterVO {
    @ApiModelProperty(value = "信息")
    private String message;
}
