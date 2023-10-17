package com.fs.dylan.enums;

import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dylan
 * @Title: AddressHeadEnum
 * @Package com.fs.dylan.enums
 * 设置表头枚举
 * @Description:
 * @date 2023/9/5
 */
public enum AddressHeadEnum {
    TAG1(0, "编号", IndexedColors.BLUE1.getIndex(), "编号"),
    TAG2(1, "省", IndexedColors.BLUE1.getIndex(), "省"),
    TAG3(2, "市", IndexedColors.GREEN.getIndex(), "市"),
    TAG4(3, "县", IndexedColors.AQUA.getIndex(), "县"),
    TAG5(4, "街道", IndexedColors.RED.getIndex(), "街道"),
    TAG6(5, "姓名", IndexedColors.RED.getIndex(), "姓名"),
    TAG7(6, "电话", IndexedColors.RED.getIndex(), "电话"),
    TAG8(7, "默认地址", IndexedColors.RED.getIndex(), "默认地址"),
    TAG9(8, "创建时间", IndexedColors.RED.getIndex(), "创建时间"),
    TAG10(9, "更新时间", IndexedColors.RED.getIndex(), "更新时间"),
    TAG11(10, "员工编号", IndexedColors.RED.getIndex(), "员工编号");

    private final Integer id;

    private final String name;

    private final Short color;

    private final String description;

    AddressHeadEnum(Integer id, String name, Short color, String description) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Short getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public static AddressHeadEnum getAddressHeadEnumById(Integer id) {
        for (AddressHeadEnum value : AddressHeadEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        return null;
    }

    public static String getNameById(Integer id) {
        for (AddressHeadEnum value : AddressHeadEnum.values()) {
            if (value.getId().equals(id)) {
                return value.getName();
            }
        }
        return null;
    }

    public static List<Integer> getHeadListById(Integer id) {
        List<Integer> list = new ArrayList<>();
        for (AddressHeadEnum value : AddressHeadEnum.values()) {
            if (value.getId().equals(id)) {
                list.add(value.getId());
            }
        }
        return list;
    }
}
