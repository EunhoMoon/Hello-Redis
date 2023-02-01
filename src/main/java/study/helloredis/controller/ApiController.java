package study.helloredis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.helloredis.dto.UserProfile;
import study.helloredis.dto.UserScore;
import study.helloredis.service.RankingService;
import study.helloredis.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final UserService userService;

    private final RankingService rankingService;

    @GetMapping("/users/{userId}/profile")
    public UserProfile getUserProfile(@PathVariable String userId) {
        return userService.getUserProfile(userId);
    }

    @PostMapping("/score")
    public Boolean setScore(@RequestBody UserScore userScore) {
        return rankingService.setUserScore(userScore.getUsername(), userScore.getScore());
    }

    @GetMapping("/rank/{username}")
    public Long getRank(@PathVariable String username) {
        return rankingService.getUserRanking(username);
    }

    @GetMapping("/rank")
    public List<String> getRank() {
        return rankingService.getTopRank(3);
    }

}
