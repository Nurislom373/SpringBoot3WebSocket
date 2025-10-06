package org.khasanof.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 10/6/25
 */
public class SimpleHandshakeHandler extends DefaultHandshakeHandler {

    /**
     *
     * @param request
     * @param wsHandler
     * @param attributes
     * @return
     */
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders(request.getHeaders());
        Principal principal = request.getPrincipal();
        if (principal == null) {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ANONYMOUS"));
            principal = new AnonymousAuthenticationToken("WebsocketConfiguration", "anonymous", authorities);
        }
        return principal;
    }
}
