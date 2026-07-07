package com.tangzhong.sample.framework.log.config;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import java.util.Map;

/**
 *
 * @author tangzhong
 * @date   2026-07-03 11:52
 * @since  V1.0.0
 */
@Configuration
public class AsyncTaskConfig {

    /**
     * 日志线程池
     */
    @Bean
    public AsyncTaskExecutor logExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Thread-Log-");
        executor.initialize();
        //同步MDC到新线程
        executor.setTaskDecorator((runnable)->{
            Map<String, String> context = MDC.getCopyOfContextMap();
            return () -> {
                if (context != null) {
                    MDC.setContextMap(context);
                }
                try {
                    runnable.run();
                } finally {
                    MDC.clear();
                }
            };
        });
        //使用DelegatingSecurityContextAsyncTaskExecutor以便于同步SecurityContextHodler到新线程
        return new DelegatingSecurityContextAsyncTaskExecutor(executor);
    }
}