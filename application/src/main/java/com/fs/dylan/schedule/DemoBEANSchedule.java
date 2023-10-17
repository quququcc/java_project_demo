package com.fs.dylan.schedule;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author dylan
 * @Title: DemoSchedule
 * @Package com.fs.dylan.schedule
 * @Description:
 * @date 2023/9/25
 */
@Component
@Slf4j
public class DemoBEANSchedule {

    @XxlJob("job_test")
    public ReturnT<String> testSchedule() {
        System.out.println("BEAN模式执行了~");
        return new ReturnT<>(ReturnT.SUCCESS_CODE, "success");
    }
}
