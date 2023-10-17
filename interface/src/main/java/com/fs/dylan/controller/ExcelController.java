package com.fs.dylan.controller;

import com.fs.dylan.pojo.dto.AddressDownloadDTO;
import com.fs.dylan.service.AddressService;
import com.fs.dylan.utils.response.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author dylan
 * @Title: ExcelController
 * @Package com.fs.dylan.controller
 * @Description:
 * @date 2023/9/26
 */
@RestController
@Api(tags = "excel导入导出")
@RequestMapping("/api/excel")
public class ExcelController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "上传excel", httpMethod = "POST")
    @PostMapping("/uploadAddress")
    public ResponseUtil<String> uploadExcel(@RequestParam("file") MultipartFile file)
    {
        addressService.uploadAddress(file);
        return ResponseUtil.success();
    }

    @ApiOperation(value = "多线程异步下载excel", httpMethod = "GET")
    @GetMapping("/downloadAddress")
    public ResponseUtil<String> downloadExcel(HttpServletResponse response)
    {
        addressService.downloadAddress(response);
        return ResponseUtil.success();
    }

    @ApiOperation(value = "动态表头下载excel", httpMethod = "POST")
    @PostMapping("/trendsDownloadAddress")
    public ResponseUtil<String> trendsDownloadAddress(@Validated @RequestBody AddressDownloadDTO dto, HttpServletResponse response)
    {
        addressService.trendsDownloadAddress(dto, response);
        return ResponseUtil.success("success");
    }
}
