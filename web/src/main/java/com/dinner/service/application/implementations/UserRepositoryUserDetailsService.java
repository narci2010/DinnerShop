package com.dinner.service.application.implementations;

import com.dinner.model.transfer.DinnerUserDTO;
import com.dinner.model.domain.user.User;
import com.dinner.repository.UserRepository;
import com.dinner.service.exception.EmailExistException;
import com.dinner.service.application.interfaces.DinnerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public User registerNewUserAccount(DinnerUserDTO serviceUser) throws EmailExistException {
        if (emailExist(serviceUser.getEmail())) {
            throw new EmailExistException("There is an account whit that email address: " + serviceUser.getEmail());
        }
        serviceUser.setPassword(passwordEncoder.encode(serviceUser.getPassword()));
        User user = new User(serviceUser);

        if (userRepository.save(user) != null) {
            return user;
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UsernameNotFoundException("Could not find user " + username);

    }


    private boolean emailExist(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        return byEmail.map(user -> user.getUsername().equals(email))
                .orElse(false);
    }
}
