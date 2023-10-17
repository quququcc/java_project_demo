package com.fs.dylan.enums;

import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.List;

/**
 * @author dylan
 * @Title: AddressEnum
 * @Package com.fs.dylan.enums
 * @Description:
 * @date 2023/9/27
 */
public enum AddressEnum {
    PROVINCE(1, "省份", IndexedColors.RED.getIndex()),
    CITY(2, "城市", IndexedColors.BLUE.getIndex()),
    DISTRICT(3, "区域", IndexedColors.GREEN.getIndex()),
    STREET(4, "街道", IndexedColors.YELLOW.getIndex()),
    NAME(5, "姓名", IndexedColors.PINK.getIndex()),
    PHONE(6, "电话", IndexedColors.GREY_25_PERCENT.getIndex());
    /**
     * 编号
     **/
    private final Integer id;

    /**
     * 名称
     **/
    private final String name;

    /**
     * 颜色
     **/
    private short color;

    AddressEnum(Integer id, String name, short color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public short getColor() {
        return color;
    }

    public static AddressEnum getById(Integer id) {
        for (AddressEnum value : AddressEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        return null;
    }

    public static Integer getHeadIdListByName(String name) {
        for (AddressEnum value : AddressEnum.values()) {
            if (value.getName().equals(name)) {
                return value.getId();
            }
        }
        return null;
    }
}
