package com.fs.dylan.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fs.dylan.constans.MagicNumberConstants;
import com.fs.dylan.entity.Address;
import com.fs.dylan.enums.AddressHeadEnum;
import com.fs.dylan.enums.ResponseCodeEnums;
import com.fs.dylan.excel.AddressImporter;
import com.fs.dylan.excel.handle.TitleColorHandler;
import com.fs.dylan.excel.listener.ImportListener;
import com.fs.dylan.exceptions.BaseException;
import com.fs.dylan.model.impl.IAddressServiceImpl;
import com.fs.dylan.pojo.bo.RegionBO;
import com.fs.dylan.pojo.dto.AddressDTO;
import com.fs.dylan.pojo.dto.AddressDownloadDTO;
import com.fs.dylan.pojo.excel.AddressExcel;
import com.fs.dylan.pojo.excel.UploadAddressExcel;
import com.fs.dylan.service.AddressService;
import com.fs.dylan.utils.ExcelUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author dylan
 * @Title: AddressServiceImpl
 * @Package com.fs.dylan.service.impl
 * @Description:
 * @date 2023/9/1
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private static final Integer LIMIT_QUERY_SIZE = 1000;

    private static final String filePath = "/Users/fs-dylan/Desktop/";

    @Autowired
    private IAddressServiceImpl addressService;

    private static final ExecutorService executorService = new ThreadPoolExecutor(10, 30, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100), new ThreadFactoryBuilder().setNameFormat("address-pool-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public Address create(AddressDTO addressDTO) {
        Address address = BeanUtil.copyProperties(addressDTO, Address.class);
        RegionBO regionBO = new RegionBO();
        regionBO.setCity(addressDTO.getCity());
        regionBO.setProvince(addressDTO.getProvince());
        regionBO.setCountry(addressDTO.getCountry());
        String s = JSONUtil.toJsonStr(regionBO);
        address.setRegion(s);

        address.setEmployeeId(12345);
        address.setId(IdWorker.getId(address));
        if (MagicNumberConstants.INT1.equals(addressDTO.getDefaultAddress())) {
            address.setDefaultAddress(MagicNumberConstants.INT1);
        } else {
            address.setDefaultAddress(MagicNumberConstants.INT0);
        }
        addressService.save(address);
        return address;
    }

    @Override
    public void uploadAddress(MultipartFile file) {
        try {
            AddressImporter addressImporter = new AddressImporter();
            ExcelUtil.save(file, addressImporter, UploadAddressExcel.class);
        } catch (Exception e) {
            throw new BaseException(ResponseCodeEnums.EXCEL_UPLOAD_ERROR, e.getMessage());
        }
    }

    @Override
    public void downloadAddress(HttpServletResponse response) {
        try {
            //先获取页数
            long count = addressService.count();
            Integer pageTotal = (int) (count % LIMIT_QUERY_SIZE == 0 ? count / LIMIT_QUERY_SIZE : count / LIMIT_QUERY_SIZE + 1 );
            List<Integer> pageList = getPageList(pageTotal);
            List<AddressExcel> addressExcelList = Collections.synchronizedList(new ArrayList<>());
            List<CompletableFuture<Void>> futureList = new ArrayList<>();
            //循环分页开启多个线程
            for (Integer page : pageList) {
                CompletableFuture<Void> listCompletableFuture = CompletableFuture.runAsync(() -> getAddressList(page, addressExcelList), executorService);
                futureList.add(listCompletableFuture);
            }

            String fileName = "addressDownload" + DateUtil.format(DateUtil.date(), "yyyy-MM-dd-HH-mm-ss") + ".xlsx";
            CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).thenRun(() -> {
                ExcelUtil.export(response, fileName, "地址下载", addressExcelList, AddressExcel.class);
            }).join();
        } catch (Exception e) {
            log.info("地址下载失败", e);
        }
    }

    private List<Integer> getPageList(Integer page) {
        List<Integer> list = new ArrayList<>();
        for (Integer i = 1; i <= page; i++) {
            list.add(i);
        }
        return list;
    }

    /**
      * @Description 获取分页数据 以及数据组装
      * @Author dylan
      * @Date 2023/9/27
      * @param [page]
      * @return java.util.List<com.fs.dylan.pojo.excel.AddressExcel>
      **/
    private void getAddressList(Integer page, List<AddressExcel> addressExcelList) {
        List<Address> addressList = addressService.page(new Page<>(page, LIMIT_QUERY_SIZE)).getRecords();
        if (CollectionUtils.isEmpty(addressList)) {
            return;
        }
        List<AddressExcel> list = addressList.stream().map(e -> {
            AddressExcel addressExcel = new AddressExcel();
            BeanUtil.copyProperties(e, addressExcel);
            RegionBO regionBO = JSON.parseObject(e.getRegion(), RegionBO.class);
            addressExcel.setProvince(regionBO.getProvince());
            addressExcel.setCity(regionBO.getCity());
            addressExcel.setArea(regionBO.getCountry());
            return addressExcel;
        }).collect(Collectors.toList());
        addressExcelList.addAll(list);
    }

    @Override
    public void trendsDownloadAddress(AddressDownloadDTO dto, HttpServletResponse response) {
        try {
            List<Integer> headList = dto.getHeadList();
            List<List<String>> headsList = new ArrayList<>();
            Map<Integer, Short> colorMap = new HashMap<>();
            List<List<Object>> dataList = new ArrayList<>();
            List<Address> list = addressService.list();
            //循环表头
            for (Integer headId : headList) {
                AddressHeadEnum addressHeadEnumById = AddressHeadEnum.getAddressHeadEnumById(headId);
                if (addressHeadEnumById == null) {
                    throw new BaseException(ResponseCodeEnums.ERROR_EXCEL_HEAD_ERROR);
                } else {
                    List<String> head = new ArrayList<>();
                    head.add(addressHeadEnumById.getName());
                    headsList.add(head);
                    colorMap.put(headId, addressHeadEnumById.getColor());
                }
            }

            //循环数据
            for (Address address : list) {
                List<Object> data = new ArrayList<>();
                //编号
                if (dto.getHeadList().contains(AddressHeadEnum.TAG1.getId())) {
                    data.add(address.getId());
                }

                String region = address.getRegion();
                RegionBO regionBO = JSON.parseObject(region, RegionBO.class);
                //省
                if (dto.getHeadList().contains(AddressHeadEnum.TAG2.getId())) {
                    data.add(regionBO.getProvince());
                }
                //市
                if (dto.getHeadList().contains(AddressHeadEnum.TAG3.getId())) {
                    data.add(regionBO.getCity());
                }
                //区
                if (dto.getHeadList().contains(AddressHeadEnum.TAG4.getId())) {
                    data.add(regionBO.getCountry());
                }
                //街道
                if (dto.getHeadList().contains(AddressHeadEnum.TAG5.getId())) {
                    data.add(address.getStreet());
                }
                //姓名
                if (dto.getHeadList().contains(AddressHeadEnum.TAG6.getId())) {
                    data.add(address.getName());
                }
                dataList.add(data);
            }

            String fileName = "AddressList" + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss");
            ExcelUtil.export(response, fileName, fileName, dataList, new TitleColorHandler(colorMap), headsList);
        } catch (Exception e) {
            log.error("地址下载失败", e);
        }
    }
}
