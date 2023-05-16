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

    @NonNull()
    @Column(name="password")
    private String password;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role")
    private String role;

    @NonNull
    @Column(name = "email")
    private String email;

    @NonNull
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


    public Users(@NonNull String password, @NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String number) {
        this.password=password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.number=number;
    }
}
