package study.helloredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HelloRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloRedisApplication.class, args);
    }

}
