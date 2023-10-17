package com.fs.dylan.model.impl;

import com.fs.dylan.entity.RoleHasPermission;
import com.fs.dylan.mapper.RoleHasPermissionMapper;
import com.fs.dylan.model.IRoleHasPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限中间表 服务实现类
 * </p>
 *
 * @author dylan
 * @since 2023-08-14
 */
@Service
public class IRoleHasPermissionServiceImpl extends ServiceImpl<RoleHasPermissionMapper, RoleHasPermission> implements IRoleHasPermissionService {

}
