package com.fs.dylan.schedule;

import cn.hutool.core.date.DateUtil;
import com.xxl.job.core.handler.IJobHandler;

/**
 * @author dylan
 * @Title: TestBeanClassSchedule
 * @Package com.fs.dylan.schedule
 * @Description:
 * @date 2023/9/27
 */
public class TestBeanClassSchedule extends IJobHandler {

    @Override
    public void execute() throws Exception {
        System.out.println("BEAN模式类模式 执行了，执行时间：" + DateUtil.now());
    }
}
