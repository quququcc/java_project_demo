package com.fs.dylan.controller;

import com.fs.dylan.entity.Employee;
import com.fs.dylan.pojo.dto.UserLoginDTO;
import com.fs.dylan.pojo.dto.UserRegisterDTO;
import com.fs.dylan.pojo.vo.UserLoginVO;
import com.fs.dylan.pojo.vo.UserRegisterVO;
import com.fs.dylan.service.AuthService;
import com.fs.dylan.utils.response.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author dylan
 * @param * @param null
 * @date 2023/8/14 11:44
 * @return
 * @return: null
 **/
@Api(tags = "Auth")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "登录", httpMethod = "POST")
    @PostMapping("/login")
    public ResponseUtil<UserLoginVO> login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        return ResponseUtil.success(authService.login(userLoginDTO));
    }

    @ApiOperation(value = "注册", httpMethod = "POST")
    @PostMapping("/register")
    public ResponseUtil<UserRegisterVO> register(@Validated @RequestBody UserRegisterDTO userRegisterDTO) {
        return ResponseUtil.success(authService.registerEmployee(userRegisterDTO));
    }

    @ApiOperation(value = "获取雇员列表", httpMethod = "GET")
    @GetMapping("/list")
    public ResponseUtil<List<Employee>> list() {
        return ResponseUtil.success(authService.list());
    }

    @ApiOperation(value = "删除雇员", httpMethod = "DELETE")
    @DeleteMapping("/employee/{id}")
    public ResponseUtil<String> remove(@PathVariable("id") Integer id) {
        boolean status = authService.removeEmployeeById(id);

        if (!status) {
            return ResponseUtil.success("Employee has been deleted");
        }

        return ResponseUtil.success("remove success");
    }

}