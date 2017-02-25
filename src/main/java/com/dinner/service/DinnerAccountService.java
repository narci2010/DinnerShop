package com.dinner.service;

import com.dinner.model.security.AuthenticationFacade;
import com.dinner.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tomek on 21-Feb-17.
 */
public class DinnerAccountService implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public void updateUserAccount() {
        accountRepository.save(authenticationFacade.getAuthentication().getAccount());
    }
}
