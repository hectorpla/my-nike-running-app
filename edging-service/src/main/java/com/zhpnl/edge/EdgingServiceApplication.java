package com.zhpnl.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by hectorlueng on 5/21/18.
 */

@SpringBootApplication
@EnableZuulProxy
public class EdgingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EdgingServiceApplication.class, args);
    }
}