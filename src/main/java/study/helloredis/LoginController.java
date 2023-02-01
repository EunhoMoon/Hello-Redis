package study.helloredis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Session redis 사용
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

//    Map<String, String> sessionMap = new HashMap<>();

    @PostMapping("/login")
    public String login(HttpSession session, @RequestBody Map<String, String> loginInfo) {
        String name = loginInfo.get("name");
        session.setAttribute("name", name);

        log.info("session={}", session.getId());
        return "saved";
    }

    @GetMapping("/username")
    public String getUsername(HttpSession session) {
        String name = (String) session.getAttribute("name");

        log.info("session={}", session.getId());
        return name;
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> 0a617cd450344d596017cb6ce8739cb9e2e2862d
