package com.fs.dylan.enums;

/**
 * @author jay.li
 * @Title: DatabaseTimeFiledEnum
 * @Package com.fs.ppt.enums
 * @Description:
 * @date 2023/8/10
 */
public enum DatabaseTimeFiledEnum {

    CREATED_AT("createdAt"),
    UPDATED_AT("updatedAt");

    private String field;

    DatabaseTimeFiledEnum(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
