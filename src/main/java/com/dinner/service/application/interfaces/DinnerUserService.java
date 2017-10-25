package com.dinner.service.application.interfaces;

import com.dinner.model.domain.user.DinnerUserDTO;
import com.dinner.model.domain.user.User;
import com.dinner.service.exception.EmailExistException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Tomek on 05-Feb-17.
 */
public interface DinnerUserService extends UserDetailsService {
    public User registerNewUserAccount(DinnerUserDTO accountDto) throws EmailExistException;
}
