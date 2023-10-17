package com.fs.dylan.model.impl;

import com.fs.dylan.entity.EmployeeHasPermission;
import com.fs.dylan.mapper.EmployeeHasPermissionMapper;
import com.fs.dylan.model.IEmployeeHasPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 雇员权限中间表 服务实现类
 * </p>
 *
 * @author dylan
 * @since 2023-08-14
 */
@Service
public class IEmployeeHasPermissionServiceImpl extends ServiceImpl<EmployeeHasPermissionMapper, EmployeeHasPermission> implements IEmployeeHasPermissionService {

}
