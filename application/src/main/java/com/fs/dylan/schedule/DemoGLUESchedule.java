package com.fs.dylan.schedule;

import com.xxl.job.core.handler.IJobHandler;

/**
 * @author dylan
 * @Title: DemoGLUESchedule
 * @Package com.fs.dylan.schedule
 * @Description:
 * @date 2023/9/27
 */
public class DemoGLUESchedule extends IJobHandler {

    @Override
    public void execute() throws Exception {
        System.out.println("GLUE模式执行了~");
    }
}
