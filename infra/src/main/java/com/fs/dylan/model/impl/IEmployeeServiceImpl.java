package com.fs.dylan.model.impl;

import com.fs.dylan.entity.Employee;
import com.fs.dylan.mapper.EmployeeMapper;
import com.fs.dylan.model.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 雇员表 服务实现类
 * </p>
 *
 * @author dylan
 * @since 2023-08-14
 */
@Service
public class IEmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
