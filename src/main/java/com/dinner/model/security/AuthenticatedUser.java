package com.dinner.model.security;

import com.dinner.model.business.Account;
import com.dinner.model.business.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Tomek on 05-Feb-17.
 */
public class AuthenticatedUser implements UserDetails {
    private String email;
    private String password;
    @Getter private String firstName;
    @Getter private String lastName;
    @Getter private Long id;
    @Getter private Account account;
    public AuthenticatedUser(User user){
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        password = user.getPassword();
        id = user.getId();
        account = user.getAccount();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
