package study.helloredis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

@SpringBootTest
class RankingServiceTest {

    @Autowired
    RankingService rankingService;

    @Test
    void inMemorySort() {
        ArrayList<Integer> list = new ArrayList<>();

        int i = 0;
        while (i <= 1_000_000) {
            int score = (int)(Math.random() * 1_000_000);
            list.add(score);
            i++;
        }

        Instant before = Instant.now();
        Collections.sort(list); // nlogn

        System.out.println("In Memory Sort : " + (Duration.between(before, Instant.now())).getNano() / 1_000_000 + "ms");
    }

    @Test
    void redisSort() {
        Instant before = Instant.now();
        rankingService.getTopRank(1_000_000);
        System.out.println("RedisSort : " + (Duration.between(before, Instant.now())).getNano() / 1_000_000 + "ms");
    }

}