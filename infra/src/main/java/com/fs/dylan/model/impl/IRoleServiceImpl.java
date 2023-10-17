package com.fs.dylan.model.impl;

import com.fs.dylan.entity.Role;
import com.fs.dylan.mapper.RoleMapper;
import com.fs.dylan.model.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author dylan
 * @since 2023-08-14
 */
@Service
public class IRoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
