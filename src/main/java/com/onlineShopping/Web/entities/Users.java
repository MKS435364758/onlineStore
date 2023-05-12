package com.onlineShopping.Web.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name="password")
    private String password;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "role")
    private String role;

    @NonNull
//  @JsonIgnore
    @Column(name = "email")
    private String email;

    @NonNull
//  @JsonIgnore
    @Column(name = "number")
    private String number;

    @NonNull
    @CreationTimestamp
//  @JsonIgnore
    @Column(name = "created_on")
    //private Date createdOn;
    private Timestamp createdOn;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Orders> orderList;

}
