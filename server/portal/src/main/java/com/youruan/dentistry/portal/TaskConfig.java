package com.youruan.dentistry.portal;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务配置
 */
@Configuration
@EnableScheduling
public class TaskConfig {

    //@Scheduled(cron = "0/1 * * * * ?")
    private void task() {
        System.out.println("------------定时任务------------");
    }

}
