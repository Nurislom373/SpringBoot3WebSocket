package org.khasanof.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 9/30/25
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    private final WebSocketAuthenticationInterceptor webSocketAuthenticationInterceptor;

    public WebSocketConfiguration(WebSocketAuthenticationInterceptor webSocketAuthenticationInterceptor) {
        this.webSocketAuthenticationInterceptor = webSocketAuthenticationInterceptor;
    }

    /**
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // /stream is the HTTP URL for the endpoint to which a WebSocket (or SockJS)
        // client needs to connect.txt for the WebSocket handshake
        registry.addEndpoint("/stream")
                .setAllowedOrigins("*")
                .withSockJS();

        /*
            registry.addEndpoint("/stream")
                .setAllowedOrigins("*")
                .setHandshakeHandler(new SimpleHandshakeHandler()) // handshake handler
                .withSockJS();
         */
    }

    /**
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // STOMP messages whose destination header begins with /app are routed to
        // @MessageMapping methods in @Controller classes
        config.setApplicationDestinationPrefixes("/app");
        // Use the built-in message broker for subscriptions and broadcasting and
        // route messages whose destination header begins with /topic or /queue to the broker
        config.enableSimpleBroker("/topic");
        config.setUserDestinationPrefix("/user");
    }

    /**
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketAuthenticationInterceptor);
    }
}
