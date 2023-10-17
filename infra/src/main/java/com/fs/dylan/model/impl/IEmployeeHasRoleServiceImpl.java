package com.fs.dylan.model.impl;

import com.fs.dylan.entity.EmployeeHasRole;
import com.fs.dylan.mapper.EmployeeHasRoleMapper;
import com.fs.dylan.model.IEmployeeHasRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 雇员角色中间表 服务实现类
 * </p>
 *
 * @author dylan
 * @since 2023-08-14
 */
@Service
public class IEmployeeHasRoleServiceImpl extends ServiceImpl<EmployeeHasRoleMapper, EmployeeHasRole> implements IEmployeeHasRoleService {

}
