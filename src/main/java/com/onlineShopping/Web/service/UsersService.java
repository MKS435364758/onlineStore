package com.onlineShopping.Web.service;

import com.onlineShopping.Web.entities.Orders;
import com.onlineShopping.Web.entities.Users;
import com.onlineShopping.Web.repository.OrdersRepository;
import com.onlineShopping.Web.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    UsersRepository usersRepository;

    OrdersRepository ordersRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void setDefaultPasswordForAllUsers() {
        String updateQuery = "UPDATE Users u SET u.password = :defaultPassword";
        Query query = entityManager.createQuery(updateQuery);
        query.setParameter("defaultPassword", "$2a$10$anlgOQX0gB28IJQGG8hWZ.husZa2phBGUp0FFS8fNq94n6B5ZAEQy");
        query.executeUpdate();
    }

    public Users saveUser(Users users) {
        if (users.getUsername() == null) users.setUsername(users.getFirstName() + "_" + users.getLastName());
        if (users.getRole() == null) users.setRole("USER");
        return usersRepository.save(users);
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email).get();
    }

    public Users getUserById(String id) {
        return usersRepository.findById(id).get();
    }

    public Users getUserByUserId(String id) {
        return usersRepository.findById(id).get();
    }

    public Users getById(String id) {
        return usersRepository.findById(id).get();
    }

    public void updateOrdersList(String userId, String orderId) {
        List<Orders> orderList = usersRepository.findById(userId).get().getOrderList();
        orderList.add(ordersRepository.findById(orderId).get());
        usersRepository.findById(userId).get().setOrderList(orderList);
    }

    public List<Orders> getOrdersMadeByUserId(String id) {
        return usersRepository.findById(id).get().getOrderList();
    }

    public void deleteUser(String id) {
        usersRepository.deleteById(id);
    }
}
