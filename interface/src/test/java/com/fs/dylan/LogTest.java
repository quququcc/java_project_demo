package com.fs.dylan;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author dylan
 * @Title: LogTest
 * @Package com.fs.dylan
 * @Description:
 * @date 2023/9/27
 */
@SpringBootTest
@Slf4j
public class LogTest {

    @Test
    public void testLog()
    {
        log.info("info log");
        log.debug("debug log");
        log.error("error log");
        log.warn("warn log");

        String str1 = "test1";
        String str2 = "test2";
        //反例子 不要使用加号拼接
        log.info("test " + str1 + ", " + str2);
        //正例子 使用占位符
        log.info("test {}, {}", "test1", "test2");
        log.error("test {}, {}", "test1", "test2");
    }

    @Test
    public void testLog2()
    {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            // 无法打印堆栈信息，不方便排查问题
            log.error("处理订单异常：{}", e.getMessage());
        }
    }

    @Test
    public void testLog3()
    {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            log.error("处理订单异常：{}", e);
        }
    }
}
