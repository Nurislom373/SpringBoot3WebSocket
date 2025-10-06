package org.khasanof.service;

import org.springframework.security.core.Authentication;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 9/30/25
 */
public interface WebSocketAuthenticationService {

    /**
     *
     * @param jwtToken
     * @return
     */
    Authentication authenticate(String jwtToken);
}
