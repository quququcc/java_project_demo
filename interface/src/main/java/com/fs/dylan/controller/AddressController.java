package com.fs.dylan.controller;

import com.fs.dylan.entity.Address;
import com.fs.dylan.pojo.dto.AddressDTO;
import com.fs.dylan.pojo.dto.AddressDownloadDTO;
import com.fs.dylan.pojo.vo.AddressVO;
import com.fs.dylan.service.AddressService;
import com.fs.dylan.utils.response.ResponseUtil;
import com.fs.dylan.validate.group.AddressCommonGroup;
import com.fs.dylan.validate.group.AddressCreateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author dylan
 * @title: AddressController
 * @projectName dylan
 * @description:
 * @date 2023/8/31
 */

@Api(tags = "地址管理")
@RestController
@RequestMapping("/ads")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "创建地址", httpMethod = "POST")
    @PostMapping("/create")
    public ResponseUtil<Address> create(@Validated({AddressCreateGroup.class, AddressCommonGroup.class}) @RequestBody  AddressDTO addressDTO) {
        Address address = addressService.create(addressDTO);
        return ResponseUtil.success(address);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新地址", httpMethod = "POST")
    public ResponseUtil<String> update()
    {
        return ResponseUtil.success("");
    }
}
