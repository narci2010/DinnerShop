package com.dinner.model.security;

import com.dinner.model.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by Tomek on 19-Feb-17.
 */
@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    @Override
    public User getAuthentication() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
