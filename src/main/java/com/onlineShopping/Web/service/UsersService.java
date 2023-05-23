package com.onlineShopping.Web.service;

import com.onlineShopping.Web.dto.request.mappers.UserRequestMapper;
import com.onlineShopping.Web.entities.Orders;
import com.onlineShopping.Web.entities.Users;
import com.onlineShopping.Web.exception.DataNotFound;
import com.onlineShopping.Web.repository.OrdersRepository;
import com.onlineShopping.Web.repository.UsersRepository;
import com.onlineShopping.Web.request.RoleUpdateRequest;
import com.onlineShopping.Web.request.UserRequest;
import com.onlineShopping.Web.response.RoleUpdateResponse;
import com.onlineShopping.Web.response.users.UsersResponse;
import com.onlineShopping.Web.tools.UnPackOptional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsersService {

    UsersRepository usersRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    UserRequestMapper usersDTOMapper;

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
            Users user = UnPackOptional.getObject(usersRepository.findByUsernameAndEmail(request.getUsername(),request.getEmail()));
            user.setRole("ADMIN");
            usersRepository.save(user);
            return new RoleUpdateResponse("Accepted",user);
        }catch (RuntimeException ex){
            return new RoleUpdateResponse("Something when wrong");
        }
    }

    public UsersResponse saveUser(UserRequest userRequest) throws ValidationException {
        Users users = usersDTOMapper.apply(userRequest);
        if(usersRepository.existsByEmail(users.getEmail())) throw new ValidationException("email already exist");
        if(usersRepository.existsByNumber(users.getNumber())) throw new ValidationException("number already exists");
        while(usersRepository.existsByUsername(users.getUsername())) {
            int r = new Random().nextInt();
            users.setUsername(users.getUsername()+r);
        }
        if (users.getUsername() == null) users.setUsername(users.getFirstName() + "_" + users.getLastName());
        users.setUsername(users.getUsername().replace(" ","_"));
        users.setRole("USER");
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        usersRepository.save(users);
        return new UsersResponse(users);
    }

    public List<UsersResponse> getAllUsers() {
        try {
            return usersRepository.findAll().stream().map(UsersResponse::new).collect(Collectors.toList());
        }catch (RuntimeException ex){
            throw new DataNotFound("something when wrong");
        }
    }


    public UsersResponse getUserByEmail(String email) {
        return new UsersResponse(UnPackOptional.getObject(usersRepository.findByEmail(email)));
    }

    public UsersResponse getUserResponseById(String id) {
        return new UsersResponse(getUserById(id));
    }

    public Users getUserById(String id){
        try {
            return UnPackOptional.getObject(usersRepository.findById(id));
        }catch (RuntimeException ex){
            throw new DataNotFound("user not found with id\t"+id);
        }
    }

    public Users getUserByUserId(String id) {
        return UnPackOptional.getObject(usersRepository.findById(id));
    }

    public Users getById(String id) {
        return UnPackOptional.getObject(usersRepository.findById(id));
    }

    public void updateOrdersList(String userId, String orderId) {
        List<Orders> orderList = UnPackOptional.getObject(usersRepository.findById(userId)).getOrderList();
        orderList.add(UnPackOptional.getObject(ordersRepository.findById(orderId)));
        UnPackOptional.getObject(usersRepository.findById(userId)).setOrderList(orderList);
    }

    public List<Orders> getOrdersMadeByUserId(String id) {
        return UnPackOptional.getObject(usersRepository.findById(id)).getOrderList();
    }

    public void deleteUser(String id) {
        usersRepository.deleteById(id);
    }
}
