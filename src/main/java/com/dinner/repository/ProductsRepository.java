package com.dinner.repository;

import com.dinner.model.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomek on 28-Jan-17.
 */
@Repository
public interface ProductsRepository extends JpaRepository<Product,Long>{
}
