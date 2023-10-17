package com.fs.dylan.pojo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author dylan
 * @Title: UploadAddressExcel
 * @Package com.fs.dylan.pojo.excel
 * @Description:
 * @date 2023/9/21
 */
@Data
@EqualsAndHashCode
@ToString
public class UploadAddressExcel {
    //省份	城市	区域	街道	姓名	电话
    @ExcelProperty(value = "省份")
    private String province;

    @ExcelProperty(value = "城市")
    private String city;

    @ExcelProperty(value = "区域")
    private String area;

    @ExcelProperty(value = "街道")
    private String street;

    @ExcelProperty(value = "姓名")
    private String name;

    @ExcelProperty(value = "电话")
    private String phone;
}
