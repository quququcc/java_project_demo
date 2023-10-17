package com.fs.dylan.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import com.alibaba.excel.write.style.row.SimpleRowHeightStyleStrategy;
import com.fs.dylan.enums.ResponseCodeEnums;
import com.fs.dylan.excel.listener.DataListener;
import com.fs.dylan.excel.listener.ImportListener;
import com.fs.dylan.excel.support.ExcelImporter;
import com.fs.dylan.exceptions.BaseException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ExcelUtil
 * @Description
 * @Author Ryan.feng
 * @Date 2022/9/16
 * @Version 1.0
 */
public class ExcelUtil {

    /**
     * 读取excel的所有sheet数据
     *
     * @param excel excel文件
     * @return List<Object>
     */
    public static <T> List<T> read(MultipartFile excel, Class<T> clazz) {
        DataListener<T> dataListener = new DataListener<>();
        ExcelReaderBuilder builder = getReaderBuilder(excel, dataListener, clazz);
        if (builder == null) {
            return null;
        }
        builder.doReadAll();
        return dataListener.getDataList();
    }

    /**
     * 读取excel的指定sheet数据
     *
     * @param excel   excel文件
     * @param sheetNo sheet序号(从0开始)
     * @return List<Object>
     */
    public static <T> List<T> read(MultipartFile excel, int sheetNo, Class<T> clazz) {
        return read(excel, sheetNo, 1, clazz);
    }

    /**
     * 读取excel的指定sheet数据
     *
     * @param excel         excel文件
     * @param sheetNo       sheet序号(从0开始)
     * @param headRowNumber 表头行数
     * @return List<Object>
     */
    public static <T> List<T> read(MultipartFile excel, int sheetNo, int headRowNumber, Class<T> clazz) {
        DataListener<T> dataListener = new DataListener<>();
        ExcelReaderBuilder builder = getReaderBuilder(excel, dataListener, clazz);
        if (builder == null) {
            return null;
        }
        builder.sheet(sheetNo).headRowNumber(headRowNumber).doRead();
        return dataListener.getDataList();
    }

    /**
     * 读取并导入数据
     *
     * @param excel    excel文件
     * @param importer 导入逻辑类
     * @param <T>      泛型
     */
    public static <T> void save(MultipartFile excel, ExcelImporter<T> importer, Class<T> clazz) {
        ImportListener<T> importListener = new ImportListener<>(importer);
        ExcelReaderBuilder builder = getReaderBuilder(excel, importListener, clazz);
        if (builder != null) {
            builder.doReadAll();
        }
    }

    /**
     * 读取并导入数据
     *
     * @param excel    excel文件
     * @param importer 导入逻辑类
     * @param <T>      泛型
     * @param batchCount 每隔 batchCount 条数据存入数据库，防止OOM
     */
    public static <T> void save(MultipartFile excel, ExcelImporter<T> importer, Class<T> clazz, int batchCount) {
        ImportListener<T> importListener = new ImportListener<>(batchCount, importer);
        ExcelReaderBuilder builder = getReaderBuilder(excel, importListener, clazz);
        if (builder != null) {
            builder.doReadAll();
        }
    }

    /**
     * 导出excel
     *
     * @param response 响应类
     * @param dataList 数据列表
     * @param clazz    class类
     * @param <T>      泛型
     */
    @SneakyThrows
    public static <T> void export(HttpServletResponse response, List<T> dataList, Class<T> clazz) {
        export(response, DateUtils.format(new Date(), DateUtils.DATE_FORMAT_14), "导出数据", dataList, clazz);
    }

    /**
     * 导出excel
     *
     * @param response  响应类
     * @param fileName  文件名
     * @param sheetName sheet名
     * @param dataList  数据列表
     * @param clazz     class类
     * @param <T>       泛型
     */
    @SneakyThrows
    public static <T> void export(HttpServletResponse response, String fileName, String sheetName, List<T> dataList, Class<T> clazz) {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Access-Control-Expose-Headers", "Content-disposition");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), clazz).sheet(sheetName).doWrite(dataList);
    }

    /**
     * @Description 导出excel 适用于动态表头
     * @Author Alex.Zhang
     * @Date 2023/6/10
     * @param [response, fileName, sheetName, dataList, head]
     * @return void
     */
    @SneakyThrows
    public static void export(HttpServletResponse response, String fileName, String sheetName, List<List<Object>> dataList, List<List<String>> head) {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Access-Control-Expose-Headers", "Content-disposition");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream())
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(23))
                .registerWriteHandler(new SimpleRowHeightStyleStrategy((short) 50, (short) 50))
                .head(head)
                .sheet(sheetName)
                .doWrite(dataList);
    }

    /**
     * @Description 导出excel 适用于动态表头
     * @Author Alex.Zhang
     * @Date 2023/6/10
     * @param [response, fileName, sheetName, dataList, head]
     * @return void
     */
    @SneakyThrows
    public static void export(HttpServletResponse response, String fileName, String sheetName, List<List<Object>> dataList, WriteHandler writeHandler, List<List<String>> head) {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Access-Control-Expose-Headers", "Content-disposition");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream())
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(23))
                .registerWriteHandler(new SimpleRowHeightStyleStrategy((short) 50, (short) 50))
                .registerWriteHandler(writeHandler)
                .head(head)
                .sheet(sheetName)
                .doWrite(dataList);
    }

    /**
     * 导出excel
     *
     * @param response     响应类
     * @param fileName     文件名
     * @param sheetName    sheet名
     * @param dataList     数据列表
     * @param clazz        class类
     * @param writeHandler 自定义处理器
     * @param <T>          泛型
     */
    @SneakyThrows
    public static <T> void export(HttpServletResponse response, String fileName, String sheetName, List<T> dataList, WriteHandler writeHandler, Class<T> clazz) {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Access-Control-Expose-Headers", "Content-disposition");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), clazz).registerWriteHandler(writeHandler).sheet(sheetName).doWrite(dataList);
    }

    /**
     * 获取构建类
     *
     * @param excel        excel文件
     * @param readListener excel监听类
     * @return ExcelReaderBuilder
     */
    public static <T> ExcelReaderBuilder getReaderBuilder(MultipartFile excel, ReadListener<T> readListener, Class<T> clazz) {
        checkExcel(excel);
        InputStream inputStream;
        try {
            inputStream = new BufferedInputStream(excel.getInputStream());
            return EasyExcel.read(inputStream, clazz, readListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取构建类
     *
     * @param excel        excel文件
     * @param readListener excel监听类
     * @return ExcelReaderBuilder
     */
    public static <T> ExcelReaderBuilder getReaderBuilder(MultipartFile excel, ReadListener<T> readListener) {
        checkExcel(excel);
        InputStream inputStream;
        try {
            inputStream = new BufferedInputStream(excel.getInputStream());
            return EasyExcel.read(inputStream, readListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void checkExcel(MultipartFile excel) {
        String filename = excel.getOriginalFilename();
        if (StringUtils.isEmpty(filename)) {
            throw new BaseException(ResponseCodeEnums.ERROR_REMIND_NO_EXCEL);
        }
        if ((!StringUtils.endsWithIgnoreCase(filename, ".xls") && !StringUtils.endsWithIgnoreCase(filename, ".xlsx"))) {
            throw new BaseException(ResponseCodeEnums.ERROR_UPLOAD_EXCEL_ERROR);
        }
    }
}
