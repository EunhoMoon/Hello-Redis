package study.helloredis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController {

    private final StringRedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String hello() {
        return "Hello Redis";
    }

    // setFruit?name=banana
    @PostMapping("/fruit")
    public String setFruit(@RequestBody Map<String, String> fruit) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("fruit", fruit.get("name"));

        return "saved";
    }

    // getFruit
    @GetMapping("/fruit")
    public String getFruit() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String fruitName = ops.get("fruit");

        log.info("name={}", fruitName);
        return fruitName;
    }

}
