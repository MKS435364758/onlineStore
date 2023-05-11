package com.onlineShopping.Web.repository;

import com.onlineShopping.Web.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,String> {

    public Optional<Users> findByEmail(String Email);
}
