package ru.tn.tnJetmanRating.security.ajax;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userService;

    @Autowired
    public AjaxAuthenticationProvider(@Qualifier("userDetailsServiceImpl") final UserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();

        UserDetails user = userService.loadUserByUsername(username);

        log.info("user: {}", user);

        if (user.getAuthorities() == null) {
            throw new InsufficientAuthenticationException("User has no roles assigned");
        } else {
            log.info("Found roles");
        }

        return new UsernamePasswordAuthenticationToken(user, user, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
