package com.onlineShopping.Web.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ADDRESS")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "number")
    private String number;

    @NonNull
    @Column(name = "line_one")
    private String lineOne;

    @Column(name = "line_two")
    private String LineTwo;

    @NonNull
    @Column(name = "city")
    private String city;

    @NonNull
    @Column(name = "state")
    private String State;

    @NonNull
    @Column(name = "zipcode")
    private String zipcode;

    @NonNull
    @Column(name = "country")
    private String country;

    @JsonIgnore
    @ManyToOne(optional = true)
    private Users user;

    @JsonIgnore
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Products> product;
}
