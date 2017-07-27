package com.dinner.repository;

import com.dinner.model.domain.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Tomek on 21-Feb-17.
 */
public interface AccountRepository extends JpaRepository<Account,Long>{
}
