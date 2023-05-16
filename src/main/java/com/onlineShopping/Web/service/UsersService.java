package com.onlineShopping.Web.service;

import com.onlineShopping.Web.entities.Orders;
import com.onlineShopping.Web.entities.Users;
import com.onlineShopping.Web.repository.OrdersRepository;
import com.onlineShopping.Web.repository.UsersRepository;
import com.onlineShopping.Web.request.RoleUpdateRequest;
import com.onlineShopping.Web.response.RoleUpdateResponse;
import com.onlineShopping.Web.response.UsersResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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



    public RoleUpdateResponse updateUserRoleByAdmin(RoleUpdateRequest request){
        try {
            Users user = usersRepository.findByUsernameAndEmail(request.getUsername(),request.getEmail()).get();
            user.setRole("ADMIN");
            usersRepository.save(user);
            return new RoleUpdateResponse("Accepted",user);
        }catch (RuntimeException ex){
            return new RoleUpdateResponse("Something when wrong");
        }
    }

    public UsersResponse saveUser(Users users) {
        if (users.getUsername() == null) users.setUsername(users.getFirstName() + "_" + users.getLastName());
        users.setRole("USER");
        users.setPassword(new BCryptPasswordEncoder().encode(users.getPassword()));
        usersRepository.save(users);
        return new UsersResponse(users);
    }

    public List<UsersResponse> getAllUsers() {
        return usersRepository.findAll().stream().map(UsersResponse::new).collect(Collectors.toList());
    }


    public UsersResponse getUserByEmail(String email) {
        return new UsersResponse(usersRepository.findByEmail(email).get());
    }

    public UsersResponse getUserResponseById(String id) {
        return new UsersResponse(getUserById(id));
    }

    public Users getUserById(String id){
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
