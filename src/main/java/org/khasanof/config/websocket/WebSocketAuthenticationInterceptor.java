package org.khasanof.config.websocket;

import lombok.RequiredArgsConstructor;
import org.khasanof.service.WebSocketAuthenticationService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static org.khasanof.util.WebSocketUtil.resolveJwtToken;

/**
 * @author Nurislom
 * @see org.khasanof.config.websocket
 * @since 9/30/25
 */
@Component
@RequiredArgsConstructor
public class WebSocketAuthenticationInterceptor implements ChannelInterceptor {

    private final WebSocketAuthenticationService webSocketAuthenticationService;

    /**
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            String jwtToken = resolveJwtToken(accessor);
            if (jwtToken != null) {
                Authentication authenticate = webSocketAuthenticationService.authenticate(jwtToken);
                accessor.setUser(authenticate);
            }
        }
        return message;
    }
}
