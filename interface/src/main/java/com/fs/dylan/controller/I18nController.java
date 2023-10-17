package com.fs.dylan.controller;

import com.fs.dylan.utils.MessageUtils;
import com.fs.dylan.utils.response.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dylan
 * @Title: I18nController
 * @Package com.fs.dylan.controller
 * @Description:
 * @date 2023/9/26
 */
@RestController
@RequestMapping("/i18n")
@Api(tags = "i18n国际化语言")
@Slf4j
public class I18nController {

    @Autowired
    private MessageUtils messageUtils;

    @ApiOperation(value = "获取国际化语言", notes = "获取国际化语言")
    @GetMapping("/getMessage")
    public ResponseUtil<String> getMessage() {
        return ResponseUtil.success(messageUtils.getMessage("resources"));
    }

    @ApiOperation(value = "获取国际化语言2", notes = "获取国际化语言2")
    @GetMapping("/getArgsMessage")
    public ResponseUtil<String> getArgsMessage() {
        return ResponseUtil.success(messageUtils.getMessage("msg", "zhangSan", "lisi"));
    }

    @ApiOperation(value = "日志记录")
    @GetMapping("/logging")
    public ResponseUtil<String> logging() {
        log.info("info log");
        return ResponseUtil.success("success");
    }
}
