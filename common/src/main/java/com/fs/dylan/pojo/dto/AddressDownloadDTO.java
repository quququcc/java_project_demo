package com.fs.dylan.pojo.dto;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author dylan
 * @Title: AddressDownloadDTO
 * @Package com.fs.dylan.pojo.dto
 * @Description:
 * @date 2023/9/5
 */
@Data
public class AddressDownloadDTO {
    @NotEmpty
    @ApiModelProperty(value = "地址下载表头", dataType = "java.util.List")
    private List<Integer> headList;
}
