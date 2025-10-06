package org.khasanof.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

import java.security.Principal;

/**
 * @author Nurislom
 * @see org.khasanof.util
 * @since 10/6/25
 */
public final class WebSocketSecurityUtil {

    /**
     *
     * @param principal
     * @return
     */
    public static Jwt getJwt(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken authenticationToken) {
            return (Jwt) authenticationToken.getCredentials();
        }
        return null;
    }
}
