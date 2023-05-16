package com.onlineShopping.Web.repository;

import com.onlineShopping.Web.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, String> {


}
