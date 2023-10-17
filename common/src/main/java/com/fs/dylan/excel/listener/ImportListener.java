package com.fs.dylan.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.fs.dylan.enums.ResponseCodeEnums;
import com.fs.dylan.excel.support.ExcelImporter;
import com.fs.dylan.exceptions.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dylan
 * @Title: Excel
 * @Package com.fs.dylan
 * @Description: ImportListener
 * @date 2023/9/21
 */
@Slf4j
@RequiredArgsConstructor
public class ImportListener<T> implements ReadListener<T> {
    /**
     * 每隔10条存储数据库，然后清理list ，方便内存回收
     **/
    private Integer batchCount = 10;

    /**
     * 缓存的数据
     **/
    private List<T> list = new ArrayList<>();

    /**
     * 实体类
     **/
    private final ExcelImporter<T> excelImporter;

    /**
     *  错误信息
     **/
    private List<String> errorList = new ArrayList<>();

    public ImportListener(Integer batchCount, ExcelImporter<T> excelImporter) {
        this.batchCount = batchCount;
        this.excelImporter = excelImporter;
    }

    /**
      * @Description 每解析一行会回调invoke()方法
      * @Author dylan
      * @Date 2023/10/8
      * @param [t, analysisContext]
      * @return void
      **/
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        // 数据校验
        if (excelImporter.check(t, analysisContext.readRowHolder().getRowIndex(), errorList)) {
            list.add(t);
        }
        if (list.size() >= batchCount) {
            excelImporter.save(list);
            // 存储完成清理 list
            list.clear();
            log.info("存储完成清理 list");
        }
    }

    /**
      * @Description 整个excel解析结束会执行doAfterAllAnalysed()方法
      * @Author dylan
      * @Date 2023/10/8
      * @param [analysisContext]
      * @return void
      **/
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //确保最后遗留的数据也要入库
        if (CollectionUtils.isNotEmpty(list)) {
            excelImporter.save(list);
        }
        log.info("所有数据解析完成！");
        // 错误通知
        if (CollectionUtils.isNotEmpty(errorList)) {
            throw new BaseException(StringUtils.join(errorList, "；"), ResponseCodeEnums.ERROR_UPLOAD_EXCEL_ERROR);
        }
    }
}
