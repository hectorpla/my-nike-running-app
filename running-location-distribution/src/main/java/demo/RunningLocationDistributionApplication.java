package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by hectorlueng on 5/12/18.
 */

@SpringBootApplication
@EnableDiscoveryClient
public class RunningLocationDistributionApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunningLocationDistributionApplication.class, args);
    }
}
