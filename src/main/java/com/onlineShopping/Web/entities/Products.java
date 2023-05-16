package com.onlineShopping.Web.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Products {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @NonNull
    @Column(name = "images")
    private String images;

    @NonNull
    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name = "listed_price")
    private BigDecimal listedPrice;


    @Column(name = "discount")
    private Double discountPercentage;

    @NonNull
    @Column(name = "availability")
    private Long availability;

    @JsonIgnore
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "address_id", referencedColumnName = "id")
    private Address address;


    @JsonIgnore
    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private List<Orders> orders;
}



