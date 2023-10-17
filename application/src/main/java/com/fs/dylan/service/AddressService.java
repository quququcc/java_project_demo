package com.fs.dylan.service;

import com.fs.dylan.entity.Address;
import com.fs.dylan.pojo.dto.AddressDTO;
import com.fs.dylan.pojo.dto.AddressDownloadDTO;
import com.fs.dylan.pojo.excel.UploadAddressExcel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author dylan
 * @Title: AddressService
 * @Package com.fs.dylan.service.impl
 * @Description:
 * @date 2023/9/1
 */

public interface AddressService {
    Address create(AddressDTO addressDTO);

    /**
      * @Description 下载地址
      * @Author dylan
      * @Date 2023/9/26
      * @param [response]
      * @return void
      **/
    void downloadAddress(HttpServletResponse response);

    /**
      * @Description 上传地址
      * @Author dylan
      * @Date \
      * @param [file]
      * @return void
      **/
    void uploadAddress(MultipartFile file);

    /**
      * @Description 动态表头下载
      * @Author dylan
      * @Date 2023/9/26
      * @param [dto]
      * @return void
      **/
    void trendsDownloadAddress(AddressDownloadDTO dto, HttpServletResponse response);
}
