package com.fs.dylan.pojo.vo;

import cn.hutool.json.JSONArray;
import com.fs.dylan.anno.AddressDefaultAnno;
import com.fs.dylan.anno.PhoneAnno;
import com.fs.dylan.validate.group.AddressCommonGroup;
import com.fs.dylan.validate.group.AddressCreateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author dylan
 * @Title: AddressVO
 * @Package com.fs.dylan.pojo.vo
 * @Description:
 * @date 2023/9/1
 */
@Data
public class AddressVO {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "用户Id")
    private Long employeeId;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "县")
    private String country;

    @ApiModelProperty(value = "街道")
    private String street;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "默认地址")
    private Integer defaultAddress;

    @ApiModelProperty(value = "区域")
    private JSONArray region;

    @ApiModelProperty(value = "创建时间")
    private String createdAt;
}
