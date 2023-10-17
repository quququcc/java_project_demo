package com.fs.dylan.pojo.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author dylan
 * @Title: AddressExcelHeadVO
 * @Package com.fs.dylan.pojo.vo
 * @Description:
 * @date 2023/9/27
 */
@Data
public class AddressExcelHeadVO {
    /**
     * 头集合
     */
    private List<List<String>> headsList;

    /**
     * 头颜色集合
     **/
    private Map<Integer, Short> colorMap;
}
