package com.fs.dylan.model;

import com.fs.dylan.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dylan
 * @since 2023-09-03
 */
public interface IAddressService extends IService<Address> {
    void saveAddress(List<Address> address);
}
