package org.khasanof.util;

import org.springframework.http.HttpHeaders;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.WebSocketHttpHeaders;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.util
 * @since 10/6/25
 */
public final class WebSocketUtil {

    /**
     * @param accessor
     * @return
     */
    public static String resolveJwtToken(StompHeaderAccessor accessor) {
        List<String> token = accessor.getNativeHeader(HttpHeaders.AUTHORIZATION);
        if (token == null) {
            return null;
        }

        String jwtToken = token.get(0);
        if (jwtToken.startsWith("Bearer ")) {
            return jwtToken.substring(7, jwtToken.length());
        }
        return null;
    }

    /**
     *
     * @param webSocketHttpHeaders
     * @return
     */
    public static String resolveJwtToken(WebSocketHttpHeaders webSocketHttpHeaders) {
        List<String> token = webSocketHttpHeaders.get(HttpHeaders.AUTHORIZATION);
        if (token.isEmpty()) {
            return null;
        }

        String jwtToken = token.get(0);
        if (jwtToken.startsWith("Bearer ")) {
            return jwtToken.substring(7, jwtToken.length());
        }
        return null;
    }
}
