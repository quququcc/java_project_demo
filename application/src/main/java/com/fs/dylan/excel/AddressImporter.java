package com.fs.dylan.excel;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fs.dylan.entity.Address;
import com.fs.dylan.excel.support.ExcelImporter;
import com.fs.dylan.model.IAddressService;
import com.fs.dylan.pojo.bo.RegionBO;
import com.fs.dylan.pojo.excel.UploadAddressExcel;
import com.fs.dylan.service.AddressService;
import com.fs.dylan.utils.StringUtil;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dylan
 * @Title: AddressImporter
 * @Package com.fs.dylan.excel
 * @Description:
 * @date 2023/9/24
 */
@Data
public class AddressImporter implements ExcelImporter<UploadAddressExcel> {

    @Autowired
    private AddressService addressService;
    @Override
    public boolean check(UploadAddressExcel data, int rowNum, List<String> errorList) {
        List<String> noticeList = new ArrayList<>();
        if (StringUtils.isBlank(data.getProvince())) {
            noticeList.add("省份为必填项");
        }
        if (CollectionUtils.isNotEmpty(noticeList)) {
            errorList.add(String.format("第%s行数据异常，%s", rowNum, StringUtils.join(noticeList, '，')));
            return false;
        }
        return true;
    }

    @Override
    public void save(List<UploadAddressExcel> list) {
        //获取IAddressService的实现类 因为AddressImporter没有加入到容器，所以无法将IAddressService注入到AddressImporter中
        IAddressService addressService = StringUtil.getBean(IAddressService.class);
        //将list转换为Address对象
        List<Address> addressList = list.stream().map(e -> {
            Address address = new Address();
            RegionBO regionBO = new RegionBO();
            regionBO.setCity(e.getCity());
            regionBO.setProvince(e.getProvince());
            regionBO.setCountry(e.getArea());
            address.setRegion(JSONUtil.toJsonStr(regionBO));
            address.setName(e.getName());
            address.setPhone(e.getPhone());
            address.setStreet(e.getStreet());
            address.setEmployeeId(12345);
            address.setId(IdWorker.getId(address));
            return address;
        }).collect(Collectors.toList());
        addressService.saveAddress(addressList);
    }
}
