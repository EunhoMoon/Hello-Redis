package study.helloredis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@RequiredArgsConstructor
@SpringBootApplication
public class HelloRedisApplication implements CommandLineRunner {

    private final ChatService service;

    public static void main(String[] args) {
        SpringApplication.run(HelloRedisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started..");

        service.enterChatRoom("chat1");
    }

}
