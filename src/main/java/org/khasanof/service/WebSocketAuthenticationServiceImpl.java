package org.khasanof.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 9/30/25
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WebSocketAuthenticationServiceImpl implements WebSocketAuthenticationService {

    private final JwtDecoder jwtDecoder;

    /**
     *
     * @param jwtToken
     * @return
     */
    @Override
    public Authentication authenticate(String jwtToken) {
        Jwt jwt = jwtDecoder.decode(jwtToken);
        return new UsernamePasswordAuthenticationToken(jwt.getSubject(), jwt, Collections.emptyList());
    }
}
