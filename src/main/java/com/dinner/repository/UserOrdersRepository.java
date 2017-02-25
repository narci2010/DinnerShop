package com.dinner.repository;

import com.dinner.model.business.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomek on 01-Feb-17.
 */
@Repository
public interface UserOrdersRepository  extends JpaRepository<UserOrder,Long> {
}
