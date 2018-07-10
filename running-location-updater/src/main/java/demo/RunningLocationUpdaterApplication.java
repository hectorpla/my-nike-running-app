package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by hectorlueng on 5/14/18.
 */

@SpringBootApplication
@EnableDiscoveryClient
public class RunningLocationUpdaterApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunningLocationUpdaterApplication.class, args);
    }
}
