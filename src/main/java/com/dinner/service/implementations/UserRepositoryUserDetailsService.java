package com.dinner.service.implementations;

import com.dinner.model.business.Account;
import com.dinner.model.business.User;
import com.dinner.model.security.AuthenticatedUser;
import com.dinner.model.security.DinnerUser;
import com.dinner.repository.UserRepository;
import com.dinner.service.exception.EmailExistException;
import com.dinner.service.interfaces.DinnerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Tomek on 05-Feb-17.
 */
@Service
public class UserRepositoryUserDetailsService implements DinnerUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthenticatedUser registerNewUserAccount(DinnerUser serviceUser) throws EmailExistException {
        if (emailExist(serviceUser.getEmail())) {
            throw new EmailExistException("There is an account whit that email address: " + serviceUser.getEmail());
        }

        User user = createNewUser(serviceUser);

        if (userRepository.save(user) != null) {
            return createAuthenticatedUser(user);
        }

        return null;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            return new AuthenticatedUser(user);
        }
        throw new UsernameNotFoundException("Could not find user " + username);

    }

    private AuthenticatedUser createAuthenticatedUser(User user) {
        AuthenticatedUser authenticatedUser = new AuthenticatedUser(user);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(authenticatedUser,
                        null,
                        new ArrayList<GrantedAuthority>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authenticatedUser;
    }

    private User createNewUser(DinnerUser serviceUser) {
        User user = new User();
        Account account = new Account(200.00);
        user.setFirstName(serviceUser.getFirstName());
        user.setLastName(serviceUser.getLastName());
        user.setPassword(passwordEncoder.encode(serviceUser.getPassword()));
        user.setEmail(serviceUser.getEmail());
        user.setAccount(account);
        return user;
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }


}
