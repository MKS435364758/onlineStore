package com.onlineShopping.Web.repository;

import com.onlineShopping.Web.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, String> {

    public Optional<Users> findByEmail(String Email);

    public boolean existsByEmail(String Email);

    public boolean existsByNumber(String number);

    public Boolean existsByUsername(String username);

    public Optional<Users> findByUsernameAndEmail(String username,String email);

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.password = :defaultPassword")
    void setDefaultPasswordForAllUsers(@Param("defaultPassword") String defaultPassword);

}
