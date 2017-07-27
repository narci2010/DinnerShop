package com.dinner.model.domain.user;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Tomek on 23-Jul-17.
 */
@Entity
@Table(name = "ROLES")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Collection<User> users;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
         return name;
    }
}
