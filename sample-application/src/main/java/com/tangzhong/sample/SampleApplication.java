package com.tangzhong.sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 10:17
 * @since  V1.0.0
 */
@EnableAsync
@SpringBootApplication
@MapperScan("com.tangzhong.sample.**.mapper")
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

}