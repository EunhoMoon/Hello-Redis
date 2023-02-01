package study.helloredis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@SpringBootTest
class RankingServiceTest {

    @Autowired
    RankingService rankingService;

    @Test
    void inMemorySort() {
        ArrayList<Integer> list = new ArrayList<>();

        int i = 0;
        while (i <= 1_000_000) {
            int score = (int) (Math.random() * 1_000_000);
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

    @Test
    void solution() {
        int[][] dots = {{1, 4}, {9, 2}, {3, 8}, {11, 6}};
        int answer = 0;

        for (int i = 0; i < dots.length; i++) {
            for (int j = i + 1; j <= 3; j++) {
                int x1 = dots[i][0] - dots[j][0];
                int y1 = dots[i][1] - dots[j][1];

                int x2 = dots[dots.length-j][0] - dots[dots.length-j][0];
                int y2 = dots[dots.length-j][1] - dots[dots.length-i][1];

                if (x1 == x2 && y1 == y2) {
                    answer++;
                }
            }
        }

        System.out.println("answer : " + answer);
    }
}