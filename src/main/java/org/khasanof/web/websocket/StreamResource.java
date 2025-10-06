package org.khasanof.web.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * @author Nurislom
 * @see org.khasanof.web.websocket
 * @since 10/6/25
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class StreamResource {

    private final SimpMessagingTemplate messagingTemplate;

    /**
     *
     * @param message
     */
    @MessageMapping("/stream/start")
    public void handleMessage(String message) {
        log.info("Received stream message: {}", message);
        messagingTemplate.convertAndSend("/topic/stream", "message: " + message);
    }

    /**
     *
     * @param message
     * @param principal
     */
    @MessageMapping("/stream/start/only/user")
    public void handleMessageOnlyUser(String message, Principal principal) {
        log.info("Received stream message: {}", message);
        messagingTemplate.convertAndSendToUser(principal.getName(), "/topic/stream", "message: " + message);
    }
}
