package com.group.ls.quartz;

import com.group.ls.quartz.listener.StartupListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author YanShuang
 * @version v_1.0.1
 * @since 2018/8/13 10:12
 */
@SpringBootApplication
@EnableScheduling
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebApplication.class);
        // 添加了一个listener，用于在spring初始化完成后，执行一段我们自定义的逻辑
//        springApplication.addListeners(new StartupListener());
        springApplication.run(args);
    }
}
