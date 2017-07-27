package com.dinner.model.security;


import com.dinner.model.domain.user.User;

/**
 * Created by Tomek on 19-Feb-17.
 */
public interface AuthenticationFacade {
    User getAuthentication();
}
