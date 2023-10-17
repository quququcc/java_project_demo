package com.fs.dylan.service;

import com.fs.dylan.entity.Employee;
import com.fs.dylan.pojo.dto.UserLoginDTO;
import com.fs.dylan.pojo.dto.UserRegisterDTO;
import com.fs.dylan.pojo.vo.UserLoginVO;
import com.fs.dylan.pojo.vo.UserRegisterVO;

import java.util.List;

/**
 * @author dylan
 * @title: AuthService
 * @projectName dylan
 * @description:
 * @date 2023/8/14
 */
public interface AuthService {

    /**
     * @description
     * @author dylan
     * @param [id]
     * @date 2023/8/14 15:14
     * @return java.lang.Boolean
     **/
    Boolean removeEmployeeById(Integer id);

    Employee getEmployeeById(String email);

    UserRegisterVO registerEmployee(UserRegisterDTO userRegisterDTO);

    UserLoginVO login(UserLoginDTO userLoginDTO);

    List<Employee> list();
}
