package com.dinner.model.domain.user;

import com.dinner.model.transfer.DinnerUserDTO;
import com.dinner.model.value.objects.Money;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Tomek on 29-Jan-17.
 */

@Entity
@Table(name = "Users")
public class User implements UserDetails, Payable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private Boolean enabled;
    private Boolean tokenExpired;


    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    private Person person;

    public User(DinnerUserDTO userDTO) {
        email = userDTO.getEmail();
        password = userDTO.getPassword();
        enabled = true;
        tokenExpired = true;
        this.person = new Person(userDTO.getFirstName(), userDTO.getLastName(), new Account(new Money(200.00, "PLN")));
        this.roles.add(new Role("ROLE_USER"));
    }

    protected User() {

    }

    @Override
    public boolean withdraw(Money money) {
        return person.account.withdraw(money);
    }

    @Override
    public void returnMoney(Money money) {
        person.account.returnMoney(money);
    }

    public Money displayUserMoney() {
        return Money.ZERO.add(person.account.money);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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
        return tokenExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
