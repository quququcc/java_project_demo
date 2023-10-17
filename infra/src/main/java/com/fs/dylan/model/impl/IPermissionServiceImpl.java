package com.fs.dylan.model.impl;

import com.fs.dylan.entity.Permission;
import com.fs.dylan.mapper.PermissionMapper;
import com.fs.dylan.model.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author dylan
 * @since 2023-08-14
 */
@Service
public class IPermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
