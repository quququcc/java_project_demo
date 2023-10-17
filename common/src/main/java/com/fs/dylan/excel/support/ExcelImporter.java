package com.fs.dylan.excel.support;

import java.util.List;

/**
 * @author dylan
 * @Title: ExcelImport
 * @Package com.fs.dylan.excel.support
 * @Description:
 * @date 2023/9/21
 */
public interface ExcelImporter<T> {

    /**
     * 数据校验
     **/
    default boolean check(T data, int rowNum, List<String> errorList) {
        return true;
    }

    /**
     * 导入数据逻辑
     **/
    void save(List<T> list);
}
