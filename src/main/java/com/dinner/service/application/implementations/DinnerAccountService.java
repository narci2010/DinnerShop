package com.dinner.service.application.implementations;

import com.dinner.model.security.AuthenticationFacade;
import com.dinner.repository.AccountRepository;
import com.dinner.service.application.interfaces.AccountService;
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
//        accountRepository.save(authenticationFacade.getAuthentication().getAccount());
    }
}
