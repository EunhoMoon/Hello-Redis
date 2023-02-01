package study.helloredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
=======

>>>>>>> 0a617cd450344d596017cb6ce8739cb9e2e2862d
@SpringBootApplication
public class HelloRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloRedisApplication.class, args);
    }

}
