package com.fs.dylan.model.impl;

import com.fs.dylan.entity.Address;
import com.fs.dylan.mapper.AddressMapper;
import com.fs.dylan.model.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dylan
 * @since 2023-09-03
 */
@Service
public class IAddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Override
    public void saveAddress(List<Address> list) {
        this.saveBatch(list);
    }
}
