package com.fs.dylan.service.impl;

import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fs.dylan.entity.Employee;
import com.fs.dylan.enums.ResponseCodeEnums;
import com.fs.dylan.exceptions.BaseException;
import com.fs.dylan.model.IEmployeeService;
import com.fs.dylan.pojo.dto.UserLoginDTO;
import com.fs.dylan.pojo.dto.UserRegisterDTO;
import com.fs.dylan.pojo.vo.UserLoginVO;
import com.fs.dylan.pojo.vo.UserRegisterVO;
import com.fs.dylan.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author dylan
 * @title: AuthServiceImpl
 * @projectName dylan
 * @description:
 * @date 2023/8/14
 */

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private IEmployeeService employeeService;

    @Value("${jwt.key}")
    private String jwtKey;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public Boolean removeEmployeeById(Integer id) {
        return employeeService.removeById(id);
    }

    @Override
    public Employee getEmployeeById(String email) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return employeeService.getOne(queryWrapper);
    }

    @Override
    public UserRegisterVO registerEmployee(UserRegisterDTO userRegisterDTO) {
        UserRegisterVO userRegisterVO = new UserRegisterVO();
        //邮箱是否存在
        if (Objects.nonNull(getEmployeeById(userRegisterDTO.getEmail()))) {
            userRegisterVO.setMessage("Email already exists");
            return userRegisterVO;
        }

        Employee employee = new Employee();
        //设置OpenId
        employee.setOpenId(userRegisterDTO.getOpenId());
        //设置邮箱
        employee.setEmail(userRegisterDTO.getEmail());
        //设置name
        employee.setName(userRegisterDTO.getName());
        //设置昵称
        employee.setNickname(userRegisterDTO.getNickName());
        //设置Job
        employee.setJob(userRegisterDTO.getJob());
        //设置头像
        employee.setAvatar(userRegisterDTO.getAvatar());

        if (employeeService.save(employee)) {
            userRegisterVO.setMessage("register success");
            return userRegisterVO;
        }
        userRegisterVO.setMessage("register failed");
        return userRegisterVO;
    }

    @Override
    public UserLoginVO login(UserLoginDTO userLoginDTO) {
        UserLoginVO userVO = new UserLoginVO();

        String email = userLoginDTO.getEmail();

        Employee employee = getEmployeeById(email);

        if (Objects.isNull(employee)) {
            throw new BaseException(ResponseCodeEnums.TOKEN_NOT_EXIST);
        }
        //生成token
        byte[] key = jwtKey.getBytes();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("expire_time", System.currentTimeMillis() + 1000 * 60 * 60 * 24);
        String token = JWTUtil.createToken(hashMap, key);
        if (Objects.isNull(token)) {
            throw new BaseException(ResponseCodeEnums.TOKEN_FAILED);
        }
        //将token存入redis
        redisTemplate.opsForValue().set(jwtKey, token, 1800, TimeUnit.SECONDS);

        //设置返回值
        userVO.setNickname(employee.getNickname());
        userVO.setName(employee.getName());
        userVO.setJob(employee.getJob());
        userVO.setAvatar(employee.getAvatar());
        userVO.setToken(token);
        return userVO;
    }

    @Override
    public List<Employee> list() {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.orderByDesc("id");
        queryWrapper.select("email", "name", "nickname", "job", "avatar");
        return employeeService.list(queryWrapper);
    }
}
