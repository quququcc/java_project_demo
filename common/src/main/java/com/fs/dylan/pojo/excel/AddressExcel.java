package com.fs.dylan.pojo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author dylan
 * @Title: AddressExcel
 * @Package com.fs.dylan.pojo.excel
 * @Description:
 * @date 2023/9/4
 */
@Data
@EqualsAndHashCode
@HeadRowHeight(15)
@ColumnWidth(20)
public class AddressExcel {

    @ExcelIgnore
    @ExcelProperty(value = "编号")
    private Long id;

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

    @ExcelIgnore
    @ExcelProperty(value = "默认地址")
    private Integer defaultAddress;

    @ExcelIgnore
    @ExcelProperty(value = "创建时间")
    private String createdAt;


    /**
     * 过滤此字段,不导出此字段
     */
    @ExcelIgnore
    @ExcelProperty(value = "更新时间")
    private String updatedAt;

    @ExcelIgnore
    @ExcelProperty(value = "用户Id")
    private String employeeId;
}
