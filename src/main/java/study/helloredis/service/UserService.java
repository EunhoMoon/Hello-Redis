package study.helloredis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import study.helloredis.dto.UserProfile;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ExternalApiService externalApiService;

    private final StringRedisTemplate redisTemplate;

    public UserProfile getUserProfile(String userId) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        String username = null;

        String cachedName = ops.get("nameKey:" + userId);

        if (StringUtils.hasText(cachedName)) {
            username = cachedName;
        } else {
            username = externalApiService.getUserName(userId);
            ops.set("nameKey:" + userId, username, 5, TimeUnit.SECONDS);
        }

        int userAge = externalApiService.getUserAge(userId);

        return new UserProfile(username, userAge);
    }

}
