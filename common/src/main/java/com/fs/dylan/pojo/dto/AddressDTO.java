package com.fs.dylan.pojo.dto;

import cn.hutool.json.JSONArray;
import com.fs.dylan.anno.AddressDefaultAnno;
import com.fs.dylan.anno.PhoneAnno;
import com.fs.dylan.validate.group.AddressCommonGroup;
import com.fs.dylan.validate.group.AddressCreateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author dylan
 * @title: AddressDTO
 * @projectName dylan
 * @description:
 * @date 2023/8/31
 */
@Data
@ApiModel(value = "地址")
public class AddressDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "省")
    @NotEmpty(groups = AddressCommonGroup.class, message = "省不能为空")
    private String province;

    @ApiModelProperty(value = "城市")
    @NotBlank(groups = AddressCommonGroup.class, message = "城市不能为空")
    private String city;

    @ApiModelProperty(value = "县")
    @NotBlank(groups = AddressCommonGroup.class, message = "县不能为空")
    private String country;

    @ApiModelProperty(value = "街道")
    @NotBlank(groups = AddressCommonGroup.class, message = "街道不能为空")
    @Size(min = 2, max = 10, groups = AddressCommonGroup.class, message = "街道长度必须在2-10之间")
    private String street;

    @ApiModelProperty(value = "姓名")
    @Size(min = 2, max = 10, groups = AddressCommonGroup.class, message = "姓名长度必须在2-10之间")
    @NotBlank(groups = AddressCommonGroup.class, message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "电话")
    @PhoneAnno(groups = {AddressCommonGroup.class, AddressCreateGroup.class})
    private String phone;

    @ApiModelProperty(value = "默认地址")
    @AddressDefaultAnno(groups = {AddressCommonGroup.class, AddressCreateGroup.class} )
    private Integer defaultAddress;
}
