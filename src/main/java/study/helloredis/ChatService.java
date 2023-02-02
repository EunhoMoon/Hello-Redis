package study.helloredis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService implements MessageListener {

    private final RedisMessageListenerContainer listenerContainer;

    private final RedisTemplate<String, String> redisTemplate;

    public void enterChatRoom(String chatRoomName) {
        listenerContainer.addMessageListener(this, new ChannelTopic(chatRoomName));

        log.info("chatRoomName={}", chatRoomName);
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("q")) {
                log.info("Quit");
                break;
            }

            redisTemplate.convertAndSend(chatRoomName, line);
        }

        listenerContainer.removeMessageListener(this);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("Message: {}", message.toString());
    }

}
