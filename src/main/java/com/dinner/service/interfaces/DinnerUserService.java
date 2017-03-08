package com.dinner.service.interfaces;

import com.dinner.model.security.AuthenticatedUser;
import com.dinner.model.security.DinnerUser;
import com.dinner.service.exception.EmailExistException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Tomek on 05-Feb-17.
 */
public interface DinnerUserService extends UserDetailsService {
    public AuthenticatedUser registerNewUserAccount(DinnerUser accountDto) throws EmailExistException;
}
